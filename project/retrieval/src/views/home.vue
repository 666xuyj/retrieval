<template>
  <el-container>
    <el-header style="font-size: 50px;" >(●'◡'●)</el-header>
    <el-container>
      <el-aside width="300px">
        <el-row >
          <span class="nav-logo" style="font-size: 50px;">😒</span>          
        </el-row>
        <div style="margin-top: 50px;"></div>
        <el-row>
          <el-button type="download" icon="el-icon-download" circle 
          @click="downLoadFile"
          ></el-button>
        </el-row>
        <div style="margin-top: 10px;"></div>
        <el-row>
          <el-button type="download" icon="el-icon-delete" circle @click="clearCheckbox">
          </el-button>
        </el-row>        
      </el-aside>
      <el-container>
        <el-main>
          <div class="input-suffix">
            <el-col :span="6">
              <el-input
                placeholder="请输入内容"
                v-model="inputcontent"
              ><el-button
                slot="append"
                icon="el-icon-search"
                @click="searchContent"
              ></el-button>   
              </el-input>              
            </el-col>          
          </div>
          
          <el-col>
            <el-tree 
            :data="analyseData" 
            default-expand-all
            :expand-on-click-node="false"
            node-key="treekey"
            icon-class="el-icon-s-promotion"
            :accordion="false"
            :indent=30
            :render-content="renderContent"
            >
              <template #default="{ node, data }">
                <span v-if="node.level === 0">
                  <el-checkbox v-model="data.checked"></el-checkbox>
                  {{ node.label }}
                </span>
                <span v-else>
                  {{ node.label }}
                </span>
              </template>
            </el-tree>
          </el-col>
        </el-main>
        <el-footer></el-footer>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
import getCompare from '@/api/getCompare' 
import getDownload from '@/api/getDownload'
import request from '@/utils/request'
export default {
  data() {
    return {
      inputcontent: '',
      analyseData: [],
      selectItem:[]

    }
  },
  created(){
    this.getAnalyse()
  },
  methods:{
    getAnalyse(){
      getCompare('amd64').then(response => {
        if(response.data.code === 200){
          var data  = response.data.data;
          for(let key in data){
            var file={
              label:"",
              children:[]
            }
            file.label = data[key].path;
            var valarray = data[key].infos;
            for(let val in valarray){
              var childrenf={
                label:""
              }
              childrenf.label = valarray[val];
              file.children.push(childrenf);
            }
            this.analyseData.push(file);
          }
        }
      }).catch(error => {
            console.log(error);
        })
    },
    renderContent: function (h, { node, data, store }) {
      var children = [];
      var self = this;
      
      Array.prototype.removeByValue = function (val) {
        for (var i = 0; i < this.length; i++) {
          if (this[i] === val) {
            this.splice(i, 1);
            i--;
          }
        }
          return this;
      }
      if (node.level === 1) {
        children.push(h('el-checkbox', {
          props: {
            value: self.selectItem.includes(node.label),
          },
          on: {
            input: (value) => {
              data.checked = value;
              if(value){
                self.selectItem.push(node.label);
                // console.log(this.selectItem);
              }else{
                self.selectItem.removeByValue(node.label);
                // console.log(this.selectItem);
              }
            }
          }
        }));
      }
      children.push(h('span', node.label));

      return h('span', {
        class: {
          'custom-tree-node': true
        }
      }, children);
    },searchContent(){
      console.log(this.inputcontent)
    },downLoadFile(){
      const filePath = 'D:\\workplace\\file\\tmp\\1.txt';
      // const encodedFilePath = encodeURIComponent(filePath);
      // getDownload(filePath);
      request({
        url:"http://localhost:8088/api/download",
        meth:"request",
        params:{
        fileName: filePath
        }
        
      }).then(response=>{
        const blob = new Blob([response.data]);
        const filename = "1.txt"
        if ('download' in document.createElement('a')) {
          const link = document.createElement('a');//创建a标签
          link.download = filename;//a标签添加属性
          link.style.display = 'none';
          link.href = URL.createObjectURL(blob);
          document.body.appendChild(link);
          link.click();//执行下载
          URL.revokeObjectURL(link.href); //释放url
          document.body.removeChild(link);//释放标签
        } else {
          navigator.msSaveBlob(blob, filename);
        }   
      })
      // console.log(this.selectItem)
    },clearCheckbox(){
      this.selectItem=[]
    }

  }
}

</script>
<style>
.custom-tree-node > span {
  padding-left: 5px;
}
.input-suffix{
  padding-bottom: 50px;
}
</style>

/**
 * @author 
 * @date 2023-06-29
 * @description home.vue组件的script独立为文件。一个home组件，没考虑多组件
 */
import getCompare from '@/api/getCompare' 
import getDownload from '@/api/getDownload'
import getPath from '@/api/getPath'
import getSelect from '@/api/getSelect'

export default {
  data() {
    return {
      inputcontent: '',
      analyseData: [],
      selectItem: [],
      fullscreenLoading: false,
      state: '',
    }
  },
  created() {
  },
  methods: {
/**
 * 函数用来获取输入框然后调用已封装的api获取数据存放在analyseData数组中
 *
 * @param {string} value 搜索框的内容
 * @return {void} 获取服务器接口的数据并存放在analyseData中
 */
    getAnalyse(value) {
      getCompare(value).then(response => {
        this.fullscreenLoading = false;
        if (response.data.code === 200) {
          var data = response.data.data;
          for (let key = 0; key < data.length; key++) {
            var file = {
              label: "",
              children: []
            };
            file.label = data[key].path;
            var valarray = data[key].infos;
            for (let val in valarray) {
              var childrenf = {
                label: ""
              };
              childrenf.label = valarray[val];
              file.children.push(childrenf);
            }
            this.analyseData.push(file);
          }
        } else {

        }
      }).catch(error => {
        this.fullscreenLoading = false;
        console.log(error);
      })
    },
    searchContent() {
      this.fullscreenLoading = true;
      this.analyseData = [];
      this.getAnalyse(this.inputcontent);
    },
/**
 * 输出栏用了elementui中的el-tree，通过analyseData来渲染el-tree，并将关键字标红
 *
 * @param {null} null
 * @return {null} null
 */
    renderContent(h, { node, data,store}) {
      Array.prototype.removeByValue = function (val) {
        for (var i = 0; i < this.length; i++) {
          if (this[i] === val) {
            this.splice(i, 1);
            i--;
          }
        }
          return this;
      }
      const keyword = this.inputcontent; 
      const label = node.label; 
      const labelWithHighlight = label.replace(new RegExp(keyword, 'gi'), '<span class="highlight">' + keyword + '</span>');
      const content = [
        h('span', {
          class: {
            'custom-tree-node': true
          },
          domProps: {
            innerHTML: labelWithHighlight
          }
        })
      ];
      if (node.level === 1) {
        content.unshift(
          h('el-checkbox', {
            props: {
              value: this.selectItem.includes(node.label)
            },
            on: {
              input: (value) => {
                data.checked = value;
                if (value) {
                  this.selectItem.push(node.label);
                } else {
                  this.selectItem.removeByValue(node.label);
                }
              }
            }
          })
        );
      }

      return h('span', content);
    },
/**
 * 下载选中的文件，因为后端文件下载是通过接口来实现的，下载是通过了一个a标签链接来辅助下载
 *
 * @param {string} filePath 文件在服务器上的位置
 * @param {string} name     下载之后的文件名
 * @param {string} inputcontent 关键字用来匹配对应行
 * @return {file} 返回文件
 */
    getdown(filePath, name, inputcontent) {
      getDownload(filePath, inputcontent).then(response => {
        const blob = new Blob([response.data]);
        const filename = name;
        if ('download' in document.createElement('a')) {
          const link = document.createElement('a');
          link.download = filename;
          link.style.display = 'none';
          link.href = URL.createObjectURL(blob);
          document.body.appendChild(link);
          link.click();
          URL.revokeObjectURL(link.href); 
          document.body.removeChild(link);
        } else {
          navigator.msSaveBlob(blob, filename);
        }   
      });
    },
/**
 * 文件会多选，所以点击下载之后用了一个循环访问数组并发送请求。
 * 同时设置下载之后的文件名：原先文件名加txt
 * @param {null} null
 * @return {file} 返回文件
 */
    downLoadFile() {
      for (var i = 0; i < this.selectItem.length; i++) {
        var filePath = this.selectItem[i];
        var lastIndex = filePath.lastIndexOf('\\');
        var fileNameWithExt = filePath.slice(lastIndex + 1);
        var fileName = fileNameWithExt.slice(0, fileNameWithExt.lastIndexOf('.')) + ".txt";
        this.getdown(filePath, fileName, this.inputcontent);
      }
    },
/**
 * 清空选中框
 */
    clearCheckbox() {
      window.location.reload();
    },
/**
 * 路径输入框，获取服务器路径和自定义输入路径
 */
    retrievalSelect() {
      this.fullscreenLoading = true;
      getPath(this.state).then(res => {
        console.log(res.data);
        if (res.data.code === 200) {
          this.fullscreenLoading = false;
          const h = this.$createElement;
          this.$notify({
            title: '检索成功',
            type: 'success'
          });
        } else if (res.data.code === 201) {
          this.fullscreenLoading = false;
          const h = this.$createElement;
          this.$notify({
            title: '路径不存在',
            type: 'warning'
          });
        }
      }).catch(error => {
        console.log(error);
      })
    },
    querySearchAsync(queryString, cb) {
      getSelect().then(res => {
        var array = res.data.data;
        var data = [];
        for (var i = 0; i < array.length; i++) {
          var t = {
            value: ""
          };
          t.value = array[i];
          data.push(t);
        }
        var results = queryString ? data.filter(this.createStateFilter(queryString)) : data;
        setTimeout(() => {
          cb(results);
        }, 1000);
      }).catch(error => {
        console.error(error);
        cb([]);
      });
    },
    createStateFilter(queryString) {
      return (state) => {
        return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    handleSelect(item) {
      this.fullscreenLoading = true;
      getPath(item.value).then(res => {
        if (res.data.code === 200) {
          this.fullscreenLoading = false;
          const h = this.$createElement;
          this.$notify({
            title: '检索成功',
            type: 'success'
          });
        }
      })
    }
  }
}
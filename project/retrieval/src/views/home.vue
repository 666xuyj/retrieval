<template>
  <el-container>
    <el-header style="font-size: 50px;">(●'◡'●)</el-header>
    <el-container>
      <el-aside width="300px">
        <el-row>
          <span class="nav-logo" style="font-size: 50px;">😒</span>
        </el-row>
        <div style="margin-top: 50px;"></div>
        <el-row>
          <el-button type="download" icon="el-icon-download" circle @click="downLoadFile"></el-button>
        </el-row>
        <div style="margin-top: 10px;"></div>
        <el-row>
          <el-button type="download" icon="el-icon-delete" circle @click="clearCheckbox" v-loading.fullscreen.lock="fullscreenLoading"></el-button>
        </el-row>
        <div style="margin-top: 10px;"></div>
      </el-aside>
      <el-container>
        <el-main>
          <div class="input-suffix">
            <el-col :span="6">
              <el-input placeholder="输入关键字" v-model="inputcontent">
                <el-button slot="append" icon="el-icon-search" @click="searchContent" v-loading.fullscreen.lock="fullscreenLoading"></el-button>
              </el-input>
            </el-col>
          </div>
          <div class="input-suffix">
            <el-col :span="6">
              <el-row>
                <el-col :span="12">
                  <el-autocomplete class="inline-input" v-model="state" :fetch-suggestions="querySearchAsync" placeholder="请输入检索目录" @select="handleSelect"></el-autocomplete>
                </el-col>
                <el-button size="medium" type="warning" icon="el-icon-grape" @click="retrievalSelect">检索</el-button>
              </el-row>
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
              :indent="30"
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
<script src="@/views/home.js"></script>
<style>
.custom-tree-node > span {
  padding-left: 5px;
}
.input-suffix{
  padding-bottom: 50px;
}
.highlight {
  color: red;
}
</style>
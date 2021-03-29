<template>
  <div style="position: fixed;">
    <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;margin-top: 20px;">
      <el-radio-button :label="false">展开</el-radio-button>
      <el-radio-button :label="true">收起</el-radio-button>
    </el-radio-group>
    <el-menu :default-active="active" @select="temp" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse" :router="isRouter">
      <el-menu-item index="/home/reciver">
        <i class="el-icon-s-promotion"></i>
        <span slot="title">接收面板</span>
      </el-menu-item>
      <el-menu-item index="/home/mypayloads">
        <i class="el-icon-star-on"></i>
        <span slot="title">我的Payloads</span>
      </el-menu-item>
      <el-menu-item index="/home/payloads">
        <i class="el-icon-tickets"></i>
        <span slot="title">内置Payloads</span>
      </el-menu-item>
      <el-menu-item index="/home/ding">
        <i class="el-icon-message-solid"></i>
        <span slot="title">钉钉配置</span>
      </el-menu-item>
      <el-menu-item index="/home/tools">
        <i class="el-icon-box"></i>
        <span slot="title">小工具</span>
      </el-menu-item>
      <el-menu-item index="/home/about">
        <i class="el-icon-info"></i>
        <span slot="title">关于</span>
      </el-menu-item>
    </el-menu>
  </div>

</template>

<script>
  export default {
    name:'Aside',
    data: function() {
      return {
        active:'/home/',
        isCollapse: false,
        isRouter: true
        }
    },
    methods: {
      handleOpen: function(key, keyPath) {
        console.log(key, keyPath)
      },
      handleClose: function(key, keyPath) {
        console.log(key, keyPath)
      },
      temp: function(index,indexPath) {
        this.active=index
      }
    }
  }
</script>

<style>
</style>

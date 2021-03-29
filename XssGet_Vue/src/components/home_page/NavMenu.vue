<template>
  <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" text-color="#409EFF" style="text-align: center;position: fixed; width: 100%;">
    <h1 style="margin-top: 20px; color: #409EFF; ">XSS 接收与管理平台</h1>
  </el-menu>
</template>

<script>
  export default {
    data: function() {
      return {activeIndex: '1',activeIndex2: '1'}
    },
    methods: {
      handleSelect:function(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style>
</style>

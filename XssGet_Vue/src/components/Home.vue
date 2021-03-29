<template>
  <el-container>
    <el-header>
      <NavMenu></NavMenu>
    </el-header>
    <el-container>
      <el-aside width="190px">
        <Aside ref="refAside"></Aside>
      </el-aside>
      <el-container>
          <el-main>
            <router-view ref="reciver"></router-view>
          </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
  import NavMenu from './home_page/NavMenu.vue'
  import Aside from './home_page/Aside.vue'

  export default {
    name: 'Home',
    components: {NavMenu,Aside},
    created:function(){
      this.initWebSocket()
    },
    methods: {
      initWebSocket: function() {
        let wPath = window.document.location.href
        let pathName =  this.$route.path
        let pos = wPath.indexOf(pathName)
        let localhostPath = wPath.substring(0, pos)

        var substring1 = "https";
        var substring2 = "http";

        if(localhostPath.includes(substring1)){
          localhostPath = localhostPath.replace('https','ws')
        }
        if(localhostPath.includes(substring2)){
          localhostPath = localhostPath.replace('http','ws')
        }

        this.websock = new WebSocket( localhostPath+"/websocket" )

        this.websock.onopen = this.websockonopen
        this.websock.onerror = this.websockonerror
        this.websock.onmessage = this.websockonmessage
        this.websock.onclose = this.websockonclose
      },
      websockonopen: function(){
        this.$notify({
          title: 'websock成功',
          message: 'websock连接成功',
          type: 'success'
        })
      },
      websockonerror: function(){
        this.$notify({
          title: 'websock失败',
          message: 'websock连接失败',
          type: 'error'
        })
      },
      websockonmessage: function(msg){

        this.$confirm(msg.data, '受害者上线', {
                  confirmButtonText: '前往接收面板',
                  cancelButtonText: '取消',
                  type: 'warning'
                }).then(() => {

                  if(this.$route.path == '/home/reciver')
                  {
                    this.$refs.reciver.loads()
                  }
                  else
                  {
                    this.$router.replace("/home/reciver")
                    this.$refs.refAside.$data.active ='/home/reciver'
                  }

                }).catch(() => {});
      },
      websockonclose: function(){
        this.$notify({
          title: 'websock关闭',
          message: 'websock连接关闭',
          type: 'warning'
        })
      }
    }
  }
</script>

<style>
  .el-scrollbar__wrap{
      overflow-x: hidden;
  }
</style>

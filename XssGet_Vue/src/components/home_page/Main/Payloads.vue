<template>
  <el-scrollbar style="height:600px; width: 100%; margin-right: 0px;">

    <el-row :gutter="30" style="margin-right: 5px;" v-loading="loading">
      <!-- upload -->
      <el-col :span="6" style="margin-top: 20px;" >
        <Upload @added="loads()"></Upload>
      </el-col>
      <!-- upload -->
      <el-col :span="6" v-for="(payloadInfo,index) in payloadInfos" style="margin-top: 20px;">

          <el-card style=" text-align: center;" shadow="hover">
            <i class="el-icon-delete" style="float: right;" @click="deletePayload(index)"></i>

            <el-tooltip placement="left">
              <div slot="content">
                <p class="abstract">文件备注: {{payloadInfo.desc}}</p>
              </div>
              <h2> {{payloadInfo.name}} </h2>
            </el-tooltip>

            <i class="el-icon-star-on" style="font-size: 25px; color: #409EFF;" v-if="payloadInfo.love" @click="toCollect(index)" ></i>
            <i class="el-icon-star-off" style="font-size: 25px;" v-if="!payloadInfo.love" @click="toCollect(index)"></i><br><br>
            <!-- <p>{{payloadInfo.desc}}</p> -->
            <el-button @click="openDialog(index)" type="primary" style="position: relative; margin-bottom: 0px;" size="mini">
              生成载荷
            </el-button>
            <el-button @click="openDrawer(index)" type="infor" style="position: relative; margin-bottom: 0px;" size="mini">
              查看详情
            </el-button>
          </el-card>
      </el-col>
    </el-row>


    <el-dialog title="一键生成Payload" :visible.sync="dialogFormVisible">
      <el-form >
        <el-form-item label="Payload:" >
          <el-input type="textarea" :rows="4" v-model="dialogPayload"></el-input>
        </el-form-item>
        <span style="font-size: smaller;">ps: 本来还想添加大小写、编码、标签选择。<br>但是没时间搞了，还有好多东西要去学！〒▽〒</span>

        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button
        type="primary"
        @click="dialogFormVisible = false"
        v-clipboard:copy="dialogPayload"
        v-clipboard:success="onCopy"
        v-clipboard:error="onError">
          一键复制
        </el-button>
      </div>
    </el-dialog>


    <el-drawer
      :title="drawerInfos.title"
      :visible.sync="drawer"
      direction="rtl"
      >
      <el-form style="margin-left: 10px;">
        <el-form-item label="文件名:">
          <el-input :value="drawerInfos.title" v-model="drawerInfos.title" disabled></el-input>
        </el-form-item>
        <el-form-item label="代码区域:">
          <el-input type="textarea" :value="drawerInfos.jscode" v-model="drawerInfos.jscode" :rows="15"></el-input>
        </el-form-item>

        <el-form-item style="text-align: center;">
          <el-button type="primary" @click="updateCode()">确定</el-button>
          <el-button type="infor" @click=" drawer=false">取消</el-button>
        </el-form-item>
      </el-form>

    </el-drawer>
  </el-scrollbar>
</template>

<script>

  import Upload from './Upload.vue'

  export default {

    name: 'Payloads',
    components: {Upload},
    data() {
      return {
		    loading: false,
        dialogFormVisible: false,
        dialogPayload: '',
        kk:false,
        drawer: false,
        payloadInfos: [],

        // 当前 抽屉中的 文件名和代码
        drawerInfos: {title:'',jscode:'',index:0}
        };
    },
    mounted:function(){
      this.loads()


    },
    methods: {


      onCopy: function(e) {
        this.$notify({
                  title: '成功',
                  message: '复制成功',
                  type: 'success'
                });
      },

      onError: function(e) {
        this.$notify({
                  title: '失败',
                  message: '复制失败',
                  type: 'error'
                });
      },

      //一键生成 payloads 函数
      openDialog: function(index) {
        let wPath = window.document.location.href
        let pathName =  this.$route.path
        let pos = wPath.indexOf(pathName)
        let localhostPath = wPath.substring(0, pos)
        this.dialogPayload = "<script src='"+localhostPath+"/api/z/"+ this.payloadInfos[index].name +"'></"+"script>"
        this.dialogFormVisible = true
      },

      openDrawer: function(index) {
        this.drawerInfos.index = index
        this.drawerInfos.title = this.payloadInfos[index].name
        this.drawerInfos.jscode = this.$base64.decode(this.payloadInfos[index].jscode)
        this.drawer = true
      },

      loads: function() {
        this.loading = true
        this.$axios.get('/payloads').then( success_resp => {
          this.loading = false
          this.payloadInfos = success_resp.data
        }).catch( fail_resp =>{} )
      },

      //修改代码函数
      updateCode: function() {
        var name = this.drawerInfos.title
        var code = this.$base64.encode(this.drawerInfos.jscode)
        this.$axios.get('update?name='+name+'&code='+code).then( s_resp =>{
          // 修改成功
          if( s_resp.data ==1 ){
            this.payloadInfos[this.drawerInfos.index].jscode = this.$base64.encode(this.drawerInfos.jscode)
            this.$notify({
              title: '成功',
              message: '修改代码成功',
              type: 'success'
            })
          }
          // 修改失败
          else{
            this.$notify({
              title: '失败',
              message: '修改代码失败',
              type: 'error'
            })
          }
        }).catch( f_resp =>{
          // 修改失败
          this.$notify({
            title: '失败',
            message: '修改代码失败',
            type: 'error'
          })
        })
        // 关闭 drawer
        this.drawer = false
      },

      // 收藏函数
      toCollect: function(index) {
        this.payloadInfos[index].love = Number(!this.payloadInfos[index].love)
        var thisName = this.payloadInfos[index].name
        var thisLove = this.payloadInfos[index].love
        this.$axios.get('/love?name='+thisName+'&love='+thisLove).then( success_resp => {
          if(success_resp.data == 1){
            this.$notify({
              title: '成功',
              message: thisLove==1?'添加收藏':'取消收藏',
              type: 'success'
            })
          }else{
            this.$notify({
              title: '失败',
              message: '操作收藏失败',
              type: 'error'
            })
          }
        }).catch(fail_resp =>{
          this.$notify({
            title: '失败',
            message: '操作收藏失败',
            type: 'error'
          })
        })
      },

      deletePayload: function(index) {
        //获取 payload名字
        var thisName = this.payloadInfos[index].name
        this.$axios.get('/delete/'+thisName).then( s_resp =>{
          if(s_resp.data == 1){
            this.$notify({
              title: '成功',
              message: '删除'+thisName+'成功',
              type: 'success'
            })
            this.loads()
          }else{
            this.$notify({
              title: '失败',
              message: '删除'+thisName+'失败',
              type: 'error'
            })
          }
        }).catch( f_resp =>{
          this.$notify({
            title: '失败',
            message: '删除'+thisName+'失败',
            type: 'error'
          })
        })
      }

    }
  };
</script>

<style>
  /* 删除icon 悬停改变颜色 */
  .el-icon-delete:hover {
    color:#409EFF;
  }
  /* 收藏icon */
  .el-icon-star-off:hover {
    color:#409EFF;
  }
  /* 文件描述提示 */
  .abstract {
    width: 100px
  }
  
  .el-scrollbar__wrap{
      overflow-x: hidden;
  }
</style>

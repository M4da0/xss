<template>
  <div>
    <el-card style=" text-align: center; height: 176px;" shadow="hover">
      <i class="el-icon-circle-plus-outline" @click="dialogTableVisible = true"></i>
    </el-card>
    <el-dialog title="添加 payloads" :visible.sync="dialogTableVisible">
      <el-form>
        <el-form-item label="文件命名:">
          <el-input style="width: 500px;" v-model="uploadData.name"></el-input>
        </el-form-item>
        <el-form-item label="文件备注:">
          <el-input style="width: 500px;" v-model="uploadData.desc"></el-input>
        </el-form-item>
        <el-form-item label="代码区域:">
          <el-input type="textarea" :rows='8' v-model="uploadData.jscode"></el-input>
        </el-form-item>
        <el-form-item style="text-align: center;">
          <el-button type="primary" @click="toUpload()">确定</el-button>
          <el-button type="infor" @click="dialogTableVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: 'Upload',
    data: function() {
      return {
        dialogTableVisible:false,
        uploadData:{name:'',desc:'',jscode:''}
        }
    },
    methods: {
      toUpload: function() {

        this.uploadData.jscode = this.$base64.encode(this.uploadData.jscode)
        this.$axios.post('/add',this.uploadData).then(success_resp =>{
          if( success_resp.data == 1) {
            this.dialogTableVisible = false
            this.$notify({
              title: '成功',
              message: '添加成功',
              type: 'success'
            })
            this.uploadData = {name:'',desc:'',jscode:'',love:0}
            this.$emit('added')
          }
        } ).catch( fail_resp => {} )

      }
    }
  }
</script>

<style>
  .el-icon-circle-plus-outline {

    font-size: 100px;
    margin-top: 20px;
    cursor: pointer;

  }

  .el-icon-circle-plus-outline:hover {
    color:#409EFF;
    font-size: 100px;
    margin-top: 20px;
    cursor: pointer;

  }
</style>

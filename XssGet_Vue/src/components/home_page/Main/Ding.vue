<template>
  <div style="text-align: center;">
    <el-tabs style="width: 50%;margin-left: 8%;margin-top: 5%;float: left;" v-model="activeName">
        <el-tab-pane label="钉钉通知" name="first" class="el_tab">
          <el-switch v-model="config.isnotice" active-text="开启" inactive-text="关闭" @change="setConfig()"></el-switch>
        </el-tab-pane>
        <el-tab-pane label="Webhook" name="second"  class="el_tab">
          <el-input v-model="config.webhook" style="width: 70%;" placeholder="请输入WebHook地址"></el-input>
          <el-button type="primary" size="medium" @click="setConfig()">确定</el-button>
        </el-tab-pane>
        <el-tab-pane label="消息内容格式" name="third" class="el_tab">
          <el-row>
            <el-col :span="span"  :offset="offset1">
              <el-card :body-style="{ padding: '0px' }">
                <img src="../../../assets/2.png" class="image">
                <div style="padding: 14px;">
                  <div class="bottom clearfix">
                    <el-radio v-model="config.msgconfig" label="0" @change="setConfig()">精简的消息</el-radio>
                  </div>
                </div>
              </el-card>
            </el-col>
            <el-col :span="span"  :offset="offset2">
              <el-card :body-style="{ padding: '0px' }">
                <img src="../../../assets/1.png" class="image">
                <div style="padding: 14px;">
                  <div class="bottom clearfix">
                    <el-radio v-model="config.msgconfig" label="1" @change="setConfig()">详细的消息</el-radio>
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </el-tab-pane>
      </el-tabs>

        <el-timeline style="width: 20%;float: right; margin-right: 35px;margin-top: 10px;">
          <el-timeline-item timestamp="第一步" placement="top">
            <el-card>
              <strong>添加钉钉机器人</strong>
              <p>电脑版钉钉进入群聊，点击群设置，点击智能群助手，添加自定义机器人</p>
            </el-card>
          </el-timeline-item>
          <el-timeline-item timestamp="第二步" placement="top">
            <el-card>
              <strong>配置钉钉机器人</strong>
              <p>设置机器人名字与群组，安全设置选择：自定义关键词，且关键词设置为host</p>
            </el-card>
          </el-timeline-item>
          <el-timeline-item timestamp="第三步" placement="top">
            <el-card>
              <strong>完成</strong>
              <p>复制机器人的Webhook值，并回到本页面中添加Webhook</p>
            </el-card>
          </el-timeline-item>
        </el-timeline>

  </div>
</template>

<script>
  export default {
    name:'Ding',
    data: function() {
      return {
              //webhook，isnotice，msgconfig
              config: {webhook:'', isnotice:false, msgconfig:0},
              span:8,
              offset1:0,
              offset2:2,
              activeName:'first'
            }
    },
    mounted:function(){
      this.getConfig()
    },
    methods: {
      getConfig: function() {
        this.$axios.get('/get/config').then(success_resp =>{
          //console.log(success_resp.data)
          this.config = success_resp.data
        }).catch(fail_resp => {})
      },
      setConfig: function() {
        this.$axios.post('/set/config',this.config).then(success_resp =>{
          if(success_resp.data ==1){
            this.$notify({
              title: '成功',
              message: '操作成功',
              type: 'success'
            })
          }
          else{
            this.$notify({
            title: '失败',
            message: '操作失败',
            type: 'error'
          })
          }
        }).catch(fail_resp =>{
          this.$notify({
            title: '失败',
            message: '操作失败',
            type: 'error'
          })
        })
      }
    }
  }
</script>

<style>
  .el_tab {
    text-align: left;
    margin-top: 30px;
  }
  .image {
    height: 330px;
    width: 230px;
  }
</style>

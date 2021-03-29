<template>

<div>

    <el-progress type="dashboard" :percentage="percentage" :color="colors"></el-progress>
    <div>
      <el-button-group>
        <el-button icon="el-icon-minus" @click="decrease"></el-button>
        <el-button icon="el-icon-plus" @click="increase"></el-button>
      </el-button-group>
    </div>

  <p style="width: 600px;margin-right: 420px;float: right;">
    <el-card shadow="always" >
          下面是简介：
    </el-card>
  </p>
  <p  v-if="zheng(percentage-33)" style="width: 600px;margin-right: 420px;float: right;">
    <el-card shadow="always" >
      这里是要开花的 MADAO，来自江苏大学，全校最菜
    </el-card>
  </p>
  <p  v-if="zheng(percentage-66)" style="width: 600px;margin-right: 420px;float: right;">
    <el-card shadow="always" >
      短发控，最喜欢绫波丽，最讨厌明日香
    </el-card>
  </p>
  <p  v-if="zheng(percentage-99)" style="width: 600px;margin-right: 420px;float: right;">
    <el-card shadow="always" >
      即使丁丁变成螺丝刀,也要笑着活下去……
    </el-card>
  </p>
</div>

</template>

<script>
  export default {
    name: 'About',
    data() {
          return {
            percentage: 0,
            colors: [
              {color: '#f56c6c', percentage: 20},
              {color: '#e6a23c', percentage: 40},
              {color: '#5cb87a', percentage: 60},
              {color: '#1989fa', percentage: 80},
              {color: '#6f7ad3', percentage: 100}
            ]
          };
        },
        methods: {
          increase() {
            this.percentage += 33;
            if (this.percentage > 100) {
              this.percentage = 100;
            }
          },
          decrease() {
            this.percentage -= 33;
            if (this.percentage < 0) {
              this.percentage = 0;
            }
          },
          zheng(per) {
            if(per<0) {
              return 0
            }
            else{return per+1}
          }
        }
  }
</script>

<style>
</style>

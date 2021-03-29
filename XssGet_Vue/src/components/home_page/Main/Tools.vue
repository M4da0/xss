<template>
  <el-collapse v-model="activeName" accordion>
    <el-collapse-item title="Base64 加解密" name="1" style="text-align: center;">
      <el-input type="textarea" :rows="5" v-model="de_text"></el-input>
      <el-button type="primary" class="button" @click="base64_en()">encode ↓</el-button>
      <el-button type="success" class="button" @click="base64_de()">decode ↑</el-button>
      <el-input type="textarea" :rows="5" v-model="en_text"></el-input>
    </el-collapse-item>
    <el-collapse-item title="JSFuck" name="2">
      <div>挖坑；</div>
      <div>挖坑。</div>
    </el-collapse-item>
    <el-collapse-item title="IP地址进制转换" name="3">
      <div>挖坑；</div>
      <div>挖坑。</div>
    </el-collapse-item>
    <el-collapse-item title="URL编码" name="4">
      <div>挖坑；</div>
      <div>挖坑。</div>
    </el-collapse-item>
  </el-collapse>
</template>

<script>
  export default {
    name:'Tools',
    data: function() {
      return {
        activeName:'1',
        de_text:'',
        en_text:''
        }
    },
    methods: {
      base64_en: function() {
        this.en_text = this.$base64.encode(this.de_text)
      },
      base64_de: function() {
        this.de_text = this.$base64.decode(this.en_text)
      }
    }
  }
</script>

<style>
  .button {
    margin-top: 10px;
    margin-bottom: 10px;
  }
</style>

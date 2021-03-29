<template>
  <div>
    <el-table
        v-loading="loading"
        :data="tableData.filter(data => !search || data.ip.toLowerCase().includes(search.toLowerCase()))"
        style="width: 100%"
        height="600"
        :default-sort = "{prop: 'time', order:'descending'}"

        >
        <el-table-column label="时间" prop="time" sortable></el-table-column>
        <el-table-column label="IP" prop="ip" width="150"></el-table-column>
        <el-table-column label="数据" prop="data" width="300"></el-table-column>
        <el-table-column label="保持连接" prop="connection"></el-table-column>

        <el-table-column label="Request">
              <template slot-scope="scope">
                <el-tooltip class="item" effect="dark" placement="left">
                    <div slot="content">
                      <p class="abstract1">host: {{scope.row.host}}</p>
                      <p class="abstract1">connection: {{scope.row.connection}}</p>

                      <p class="abstract1">userAgent: {{ scope.row.userAgent}}</p>
                      <p class="abstract1">accept: {{ scope.row.accept}}</p>

                      <p class="abstract1">acceptEncoding: {{scope.row.acceptEncoding}}</p>
                      <p class="abstract1">cookie: {{scope.row.cookie}}</p>
                      <p class="abstract1">time: {{scope.row.time}}</p>
                      <p class="abstract1">ip: {{scope.row.ip}}</p>
                      <p class="abstract1">data: {{scope.row.data}}</p>
                    </div>
                    <el-tag size="medium" @click="handleClick(scope.row)">HTTP请求信息</el-tag>
                </el-tooltip>
              </template>
        </el-table-column>

        <el-table-column
          align="right">
          <template slot="header" slot-scope="scope">
            <el-input
              v-model="search"
              size="mini"
              placeholder="输入关键字搜索"/>
          </template>
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
          </template>
        </el-table-column>
      </el-table>
  </div>
</template>

<script>
  export default {
    name:'Reciver',
    data() {
      return {
        loading: false,
        //{host,connection,userAgent,accept,acceptEncoding,cookie,time,data,ip}
        tableData: [],
        search: ''
      }
    },
    mounted:function(){
      this.loads()
    },
    methods: {
      handleDelete(index, row) {
        console.log(row.time)
        this.$axios.get('/del?time='+row.time).then( success_resp =>{
          if( success_resp.data == 1 ){
            this.$notify({
              title: '成功',
              message: '删除成功',
              type: 'success'
            })
            this.loads()
          }
          else{
            this.$notify({
              title: '失败',
              message: '删除失败',
              type: 'error'
            })
          }
        }).catch( fail_resp =>{
          this.$notify({
            title: '失败',
            message: '删除失败',
            type: 'error'
          })
        })
      },
      handleClick(row) {
        console.log(row)
      },
      loads() {
        this.loading = true
        this.$axios.get('/all').then( success_resp => {
          this.loading = false
          this.tableData = success_resp.data
        }).catch( fail_resp =>{} )
      }
    },
  }
</script>

<style>
  .abstract1 {
    width: 300px
  }
</style>

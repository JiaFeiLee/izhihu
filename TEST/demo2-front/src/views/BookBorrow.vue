<template>
  <div>
    <div class="title">
      <el-breadcrumb class="titleFont">
        <el-breadcrumb-item :to="{ path: '/' }"><i class="el-icon-house" />图书借阅</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="BookBorrow">
      <el-card>
        <!-- <div class="creaBook"><el-button>新增图书</el-button></div> -->
        <div class="searchBook">
          <span>图书名称：</span>
          <el-input v-model="input"
                    placeholder="输入图书名称查询"
                    class="searchInput"></el-input>
          <el-button type="primary"
                     class="searchBut">搜索</el-button>
        </div>
        <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                  style="width: 100%;min-height:500px">
          <el-table-column prop="name"
                           label="图书名称"
                           width="180">
          </el-table-column>
          <el-table-column prop="publisher"
                           label="出版社"
                           width="180">
          </el-table-column>
          <el-table-column prop="author"
                           label="作者">
          </el-table-column>
          <el-table-column prop="stock"
                           label="库存">
          </el-table-column>
          <el-table-column prop="opera"
                           label="操作">
            <template slot-scope="scope">
              <el-button type="text"
                         @click="borrow(scope.$index, scope.row)">借阅</el-button>
            </template>

          </el-table-column>
        </el-table>
        <div class="Pagination">
          <el-pagination @size-change="handleSizeChange"
                         @current-change="handleCurrentChange"
                         :current-page="currentPage"
                         :page-sizes="[10, 20, 30, 40]"
                         :page-size="pagesize"
                         layout="total, sizes, prev, pager, next, jumper"
                         :total="parseInt(tableData.length)">
          </el-pagination>
        </div>
      </el-card>
    </div>
    <div class="footer">Copyright ©2016-2019 厦门理工学院 版权所有</div>
  </div>
</template>

<script>
import { reqBookInfo, reqBorrow } from '../api/home'
import { mkdir } from 'fs';
export default {
  name: 'BookBorrow',
  data () {
    return {
      input: '',
      tableData: [],
      pagesize: 10,
      currentPage: 1
    }
  },
  created () {
    this._loadData()
  },
  methods: {
    _loadData () {
      let params = 'keyword=' + this.input
      reqBookInfo(params).then(res => {
        if (res.resultCode == 200) {
          console.info(res.data)
          this.tableData = res.data
        }
      })
    },
    search () {
      this._loadData()
    },
    handleSizeChange (val) {
      this.pagesize = val
    },
    handleCurrentChange () {
      this.currentPage = val
    },
    borrow (index, row) {
      let params = {
        userId: this.$store.state.user.id,
        bookId: row.id
      }
      reqBorrow(params).then(res => {
        if (res.resultCode == 200) {
          this.$message({
            type: 'success',
            message: res.resultMessage
          })
          this._loadData()
        }
      })
    }
  },
}
</script>

<style lang="scss">
.BookBorrow {
  min-width: 1000px;
  margin: 20px 20px;
  .searchBook {
    height: 40px;
    .searchBut {
      padding: 5px 10px;
      margin-left: 10px;
    }
    .el-input__inner {
      height: 25px;
    }
    .searchInput {
      width: 250px;
    }
  }
  .Pagination {
    float: right;
    margin-top: 30px;
    margin-bottom: 15px;
  }
}
.title {
  background: white;
  width: 1223px;
  height: 40px;
  .titleFont {
    padding-top: 13px;
    padding-left: 10px;
  }
}
.footer {
  text-align: center;
  margin-bottom: 10px;
}
</style>

<template>
    <div class="content-wrapper">
        <div class="container-fluid">
            <!-- Breadcrumb-->
            <div class="row pt-2 pb-2">
                <div class="col-sm-9">
                    <h4 class="page-title">申请报修</h4>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">物业报修</li>
                        <li class="breadcrumb-item active" aria-current="page">申请报修</li>
                    </ol>
                </div>
            </div>
            <!-- End Breadcrumb-->
            <div class="row justify-content-center">
                <div class="card col-lg-12">
                    <div class="card-body">
                        <div class="card-title text-primary">新增报修</div>
                        <hr>
                        <form>
                            <div class="form-group">
                                <label>报修ID</label>
                                <input type="text" class="form-control" placeholder="请填写报修ID" v-model="fix.id">
                            </div>
                            <div class="form-group">
                                <label>报修用户ID</label>
                                <input type="text" class="form-control" placeholder="报修用户ID" v-model="fix.userid">
                            </div>
                            <div class="form-group">
                                <label>报修类型</label>
                                <select class="form-control" v-model="fix.type">
                                    <option value="水管维修">水管维修</option>
                                    <option value="电路维修">电路维修</option>
                                    <option value="门窗维修">门窗维修</option>
                                    <option value="其他">其他</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>报修金额(元)</label>
                                <input type="number" class="form-control" placeholder="填写报修金额" v-model="fix.amount">
                            </div>
                            <div class="form-group">
                                <label>报修期限</label>
                                <input type="date" class="form-control" placeholder="填写报修期限" v-model="fix.deadline">
                            </div>



                            <br>
                            <div class="form-group">
                                <button type="button" @click="submit"
                                    class="btn btn-primary shadow-primary px-5 col-lg-12">
                                    <i class="icon-lock"></i>提交</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>


        </div>
        <!-- End container-fluid-->

    </div>
</template>
<script scoped>
import axios from 'axios';
export default {
    data() {
        return {
            fix: {
                id: '',
                userid: '',
                type: '',
                amount: '',
                deadline: '',
                status: '未处理'
            },
            URL: 'http://localhost:8086/fix/'
        }
    },
    methods: {
        checknullvalue() {
            if (this.fix.id == '' || this.fix.userid == '' || this.fix.type == '' || this.fix.amount == '' || this.fix.deadline == '') {
                alert('请填写完整信息');
                return false;
            }
            return true;
        },
        // methods
        submit() {
            var check = this.checknullvalue();
            if (!check) {
                return;
            }

            // 将 date 格式转换为 LocalDateTime 格式
            let date = new Date(this.fix.deadline);
            this.fix.deadline = date.toISOString().replace('Z', '');

            axios.post(this.URL, this.fix).then(res => {

                if (res.data != '' && res.status == 200) {
                    alert('新增报修成功');



                } else {
                    alert('新增报修失败,请检查报修ID是否已存在');



                }
            }).catch(err => {
                console.error(err);
            });

        }
    }
}
</script>
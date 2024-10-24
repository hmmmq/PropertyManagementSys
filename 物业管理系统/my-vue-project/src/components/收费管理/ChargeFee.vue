<template>
    <div class="content-wrapper">
        <div class="container-fluid">
            <!-- Breadcrumb-->
            <div class="row pt-2 pb-2">
                <div class="col-sm-9">
                    <h4 class="page-title">收费管理</h4>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">收费管理</li>
                        <li class="breadcrumb-item active" aria-current="page">收费执行</li>
                    </ol>
                </div>
            </div>
            <!-- End Breadcrumb-->
            <div class="row justify-content-center">
                <div class="card col-lg-12">
                    <div class="card-body">
                        <div class="card-title text-primary">新增收费</div>
                        <hr>
                        <form>
                            <div class="form-group">
                                <label>收费ID</label>
                                <input type="text" class="form-control" placeholder="请填写收费ID" v-model="bill.id">
                            </div>
                            <div class="form-group">
                                <label>收费用户ID</label>
                                <input type="text" class="form-control" placeholder="收费用户ID" v-model="bill.userid">
                            </div>
                            <div class="form-group">
                                <label>收费类型</label>
                                <select class="form-control" v-model="bill.type">
                                    <option value="水费">水费</option>
                                    <option value="电费">电费</option>
                                    <option value="燃气费">燃气费</option>
                                    <option value="物业费">物业费</option>
                                    <option value="其他">其他</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>收费金额(元)</label>
                                <input type="number" class="form-control" placeholder="填写收费金额" v-model="bill.amount">
                            </div>
                            <div class="form-group">
                                <label>收费期限</label>
                                <input type="date" class="form-control" placeholder="填写收费期限" v-model="bill.deadline">
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
            bill: {
                id: '',
                userid: '',
                type: '',
                amount: '',
                deadline: '',
                status: '未缴费'
            },
            URL: 'http://localhost:8086/fee/'
        }
    },
    methods: {
        checknullvalue() {
            if (this.bill.id == '' || this.bill.userid == '' || this.bill.type == '' || this.bill.amount == '' || this.bill.deadline == '') {
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
            let date = new Date(this.bill.deadline);
            this.bill.deadline = date.toISOString().replace('Z', '');

            axios.post(this.URL, this.bill).then(res => {

                if (res.data != '' && res.status == 200) {
                    alert('新增收费成功');
                } else {
                    alert('新增收费失败,请检查收费ID是否已存在');



                }
            }).catch(err => {
                console.error(err);
            });

        }
    }
}
</script>
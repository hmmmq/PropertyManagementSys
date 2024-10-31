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
            <button type="button" class="btn btn-outline-info waves-effect waves-light m-1"
                @click="cancel">取消修改</button>
            <!-- End Breadcrumb-->
            <div class="row justify-content-center">
                <div class="card col-lg-12">
                    <div class="card-body">
                        <div class="card-title text-primary">修改收费</div>
                        <hr>
                        <form>
                            <div class="form-group">
                                <label>收费ID</label>
                                <input type="text" class="form-control" placeholder="请填写收费ID" v-model="fee.id" disabled>
                            </div>
                            <div class="form-group">
                                <label>收费用户ID</label>
                                <input type="text" class="form-control" placeholder="收费用户ID" v-model="fee.userid">
                            </div>
                            <div class="form-group">
                                <label>收费类型</label>
                                <select class="form-control" v-model="fee.type">
                                    <option value="水费">水费</option>
                                    <option value="电费">电费</option>
                                    <option value="燃气费">燃气费</option>
                                    <option value="物业费">物业费</option>
                                    <option value="其他">其他</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>收费金额(元)</label>
                                <input type="number" class="form-control" placeholder="填写收费金额" v-model="fee.amount">
                            </div>
                            <div class="form-group">
                                <label>收费期限</label>
                                <input type="date" class="form-control" placeholder="填写收费期限" v-model="fee.deadline">
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
    props: {
        initialfee: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            fee: {
                id: this.initialfee.id || '',
                userid: this.initialfee.userid || '',
                type: this.initialfee.type || '',
                amount: this.initialfee.amount || '',
                deadline: this.initialfee.deadline || '',
                status: this.initialfee.status || ''
            },
            URL: 'http://localhost:8086/fee/'
        }
    },
    methods: {
        cancel() {
            this.$emit('data-back-fee', true);
        },
        checknullvalue() {
            if (this.fee.id == '' || this.fee.userid == '' || this.fee.type == '' || this.fee.amount == '' || this.fee.deadline == '') {
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
            let date = new Date(this.fee.deadline);
            this.fee.deadline = date.toISOString().replace('Z', '');
            this.URL = this.URL + this.fee.id;
            axios.put(this.URL, this.fee).then(res => {

                if (res.data != '' && res.status == 200) {
                    alert('修改收费成功');
                    this.fee = {
                        id: '',
                        userid: '',
                        type: '',
                        amount: '',
                        deadline: '',
                        status: '未缴费'
                    };
                    this.$emit('data-back-fee', true);
                } else {
                    alert('修改收费失败,请检查收费ID是否已存在');



                }
            }).catch(err => {
                console.error(err);
            });

        }
    }
}
</script>
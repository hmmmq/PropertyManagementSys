<template>
    <div class="content-wrapper">
        <div class="container-fluid">
            <!-- Breadcrumb-->
            <div class="row pt-2 pb-2">
                <div class="col-sm-9">
                    <h4 class="page-title">合同管理</h4>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">合同管理</li>
                        <li class="breadcrumb-item active" aria-current="page">新增合同</li>
                    </ol>
                </div>
            </div>
            <!-- End Breadcrumb-->
            <div class="row justify-content-center">
                <div class="contractd col-lg-12">
                    <div class="contractd-body">
                        <div class="contractd-title text-primary">新增合同</div>
                        <hr>
                        <form>
                            <div class="form-group">
                                <label>合同ID</label>
                                <input type="number" class="form-control" placeholder="请填写合同ID" v-model="contract.id">
                            </div>
                            <div class="form-group">
                                <label>合同名称</label>
                                <input type="text" class="form-control" placeholder="合同名称" v-model="contract.name">
                            </div>

                            <div class="form-group">
                                <label>合同用户ID</label>
                                <input type="text" class="form-control" placeholder="合同用户ID"
                                    v-model="contract.signUserid">
                            </div>

                            <div class="form-group">
                                <label>合同类型</label>
                                <select class="form-control" v-model="contract.type">
                                    <option value="租赁合同">租赁合同</option>
                                    <option value="销售合同">销售合同</option>
                                    <option value="其他">其他</option>
                                </select>
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
            contract: {
                id: '',
                name: '',
                signUserid: '',
                signUsername: '',
                type: '',
                signTime: '',
                status: '未签署'
            },
            URL: 'http://localhost:8086/contract/'
        }
    },
    methods: {
        checknullvalue() {
            if (this.contract.id == '' || this.contract.userid == '' || this.contract.type == '' || this.contract.number == '') {
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

            axios.post(this.URL, this.contract).then(res => {

                if (res.data != '' && res.data != false) {
                    alert('新增合同成功');
                    this.contract = {
                        id: '',
                        name: '',
                        signUserid: '',
                        signUsername: '',
                        type: '',
                        signTime: '',
                        status: '未签署'
                    };
                } else {
                    alert('新增合同失败,请检查合同ID是否已存在或者用户ID是否存在');
                }
            }).catch(err => {
                console.error(err);
            });

        }
    }
}
</script>
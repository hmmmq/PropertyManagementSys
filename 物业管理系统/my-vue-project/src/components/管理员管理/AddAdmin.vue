<template>
    <div class="content-wrapper">
        <div class="container-fluid">
            <!-- Breadcrumb-->
            <div class="row pt-2 pb-2">
                <div class="col-sm-9">
                    <h4 class="page-title">业主信息管理</h4>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">管理员管理</li>
                        <li class="breadcrumb-item active" aria-current="page">新增业主信息</li>
                    </ol>
                </div>
            </div>
            <!-- End Breadcrumb-->
            <div class="row justify-content-center">
                <div class="card col-lg-12">
                    <div class="card-body">
                        <div class="card-title text-primary">新增管理员</div>
                        <hr>
                        <form>
                            <div class="form-group">
                                <label>管理员ID(作为登录账号使用)</label>
                                <input type="number" class="form-control" placeholder="请填写管理员ID" v-model="admin.id">
                            </div>
                            <div class="form-group">
                                <label>管理员密码</label>
                                <input type="text" class="form-control" placeholder="填写管理员密码" v-model="admin.password">
                            </div>
                            <div class="form-group">
                                <label>管理员姓名</label>
                                <input type="text" class="form-control" placeholder="填写管理员姓名" v-model="admin.name">
                            </div>
                            <div class="form-group">
                                <label>管理员手机号</label>
                                <input type="number" class="form-control" placeholder="填写管理员手机号" v-model="admin.phone">
                            </div>

                            <div class="form-group">
                                <label>管理员邮箱</label>
                                <input type="text" class="form-control" placeholder="填写管理员邮箱" v-model="admin.email">
                            </div>
                            <div class="form-group">
                                <label>管理员性别</label>
                                <select class="form-control" v-model="admin.gender">
                                    <option value="男">男</option>
                                    <option value="女">女</option>
                                </select>
                            </div>
                            <br>
                            <div class="form-group">
                                <button type="button" @click="register"
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
            // data
            admin: {
                id: '',
                password: '',
                name: '',
                age: '',
                position: '',
                phone: '',
                email: '',
                gender: '',
                type: true
            },
            URL: 'http://localhost:8086/admin/'
        }
    },
    methods: {
        checknullvalue() {
            if (this.admin.password == '' || this.admin.name == '' ||
                this.admin.phone == '' || this.admin.email == '' || this.admin.id == '' || this.admin.gender == '') {
                alert('请填写完整信息');
                return false;
            }
            return true;
        },
        // methods
        register() {
            var check = this.checknullvalue();
            if (!check) {
                return;
            }

            axios.post(this.URL, this.admin).then(res => {
                if (res.data != '' && res.data == true) {

                    alert('新增管理员成功');
                    this.admin = {
                        id: '',
                        password: '',
                        name: '',
                        age: '',
                        position: '',
                        phone: '',
                        email: '',
                        gender: '',
                        type: true
                    };
                } else {
                    alert('新增管理员失败,请检查管理员ID是否已存在');
                }
            }).catch(err => {
                console.error(err);
            });

        }
    }
}
</script>
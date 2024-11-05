<template>
    <div class="content-wrapper">
        <div class="container-fluid">
            <!-- Breadcrumb-->
            <div class="row pt-2 pb-2">
                <div class="col-sm-9">
                    <h4 class="page-title">管理员信息管理</h4>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">管理员管理</li>
                        <li class="breadcrumb-item active" aria-current="page">修改管理员信息</li>
                    </ol>
                </div>
            </div>
            <button type="button" class="btn btn-outline-info waves-effect waves-light m-1"
                @click="cancel">取消修改</button>
            <!-- End Breadcrumb-->
            <div class="row justify-content-center">
                <div class="card col-lg-12">
                    <div class="card-body">
                        <div class="card-title text-primary">修改管理员</div>
                        <hr>
                        <form>
                            <div class="form-group">
                                <label>管理员ID(作为登录账号使用)</label>
                                <input type="number" class="form-control" placeholder="请填写管理员ID" v-model="admin.id"
                                    disabled>
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

                            <div class="form-group">
                                <label>是否是管理员</label>
                                <select class="form-control" v-model="admin.type">
                                    <option value="true">是</option>
                                    <option value="false">否</option>
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
    props: {
        initialadmin: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            // data
            admin: {
                id: this.initialadmin.id || '',
                password: this.initialadmin.password || '',
                name: this.initialadmin.name || '',
                age: this.initialadmin.age || '',
                phone: this.initialadmin.phone || '',
                email: this.initialadmin.email || '',
                gender: this.initialadmin.gender || '',
                type: this.initialadmin.type || true
            },
            URL: 'http://localhost:8086/admin/'
        }
    },
    methods: {
        cancel() {
            this.$emit('data-back-admin', true);
        },
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
            // register
            this.URL = this.URL + this.admin.id;

            axios.put(this.URL, this.admin).then(res => {
                if (res.data != '' && res.data == true) {
                    alert('修改管理员成功');
                    this.admin = {
                        id: '',
                        password: '',
                        name: '',
                        age: '',
                        phone: '',
                        email: '',
                        gender: '',
                        type: true
                    };
                    this.$emit('data-back-admin', true);

                } else {
                    alert('修改管理员失败,请检查管理员ID是否已存在');
                }
            }).catch(err => {
                console.error(err);
            });

        }
    }
}
</script>
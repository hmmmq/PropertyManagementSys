<template>
    <div class="card-body">
        <div class="card-content p-2">
            <div class="text-center">
                <img src="http://127.0.0.1:8083/images/logo-icon.png" alt="logo icon">
            </div>
            <div class="card-title text-uppercase text-center py-3">物业管理系统</div>
            <form>
                <div class="form-group">
                    <label for="exampleInputUsername" class="">账号</label>
                    <div class="position-relative has-icon-right">
                        <input type="text" id="exampleInputUsername" class="form-control input-shadow"
                            placeholder="请输入账号" v-model="user.id">
                        <div class="form-control-position">
                            <i class="icon-user"></i>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword" class="">密码</label>
                    <div class="position-relative has-icon-right">
                        <input type="password" id="exampleInputPassword" class="form-control input-shadow"
                            placeholder="请输入密码" v-model="user.password">
                        <div class="form-control-position">
                            <i class="icon-lock"></i>
                        </div>
                    </div>
                </div>
                <!-- 选择是否为管理员 -->
                <div class="form-group">
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="custom-control-input" id="customCheck1" v-model="user.type">
                        <label class="custom-control-label" for="customCheck1">管理员登录</label>
                    </div>
                </div>

                <button type="button" class="btn btn-primary shadow-primary btn-block waves-effect waves-light"
                    @click="Login">登录</button>
            </form>
        </div>
    </div>
    <div class="card-footer text-center py-3">
        <p class="text-muted mb-0">没有账户? <a href="#"> 联系管理员创建账户 </a></p>
    </div>
</template>
<script scoped>
import axios from 'axios';
export default {
    name: 'LoginForm',
    data() {
        return {
            user: {
                id: '',
                password: '',
                name: '',
                age: '',
                phone: '',
                email: '',
                gender: '',
                type: false
            },
            userurl: 'http://localhost:8086/user/login',
            adminurl: 'http://localhost:8086/admin/login',
            url: ''

        }
    },

    methods: {

        Login() {
            if (this.user.type) {
                this.url = this.adminurl;
            } else {
                this.url = this.userurl;
            }


            if (this.user.username == '' || this.user.password == '') {
                alert('账号或密码不能为空');
                return;
            }

            axios.post(this.url, this.user).then(res => {
                console.log(res);
                if (res.status == 200 && res.data != '' && res.data != null) {
                    this.user = res.data;
                    localStorage.setItem('user', JSON.stringify(this.user));
                    this.$router.push('/user').then(() => {
                        this.$router.go(0);
                    });
                } else {
                    alert('账号或密码错误');
                    return;
                }
            }).catch(err => {
                console.log(err);
                return;
            });

        }

    },

}
</script>
<style scoped></style>

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
                <div class="form-row">


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
                type: true
            },
            user1: {
                id: '1',
                password: '123456',
                name: '张三',
                age: '30',
                position: '初级管理员',
                phone: '13800138000',
                registerTime: '2023-01-01T10:00:00Z',
                onlineTime: '2023-01-01T10:30:00Z',
                loginTime: '2023-01-01T10:00:00Z',
                workStatus: true,
                offlineTime: '2023-01-01T18:00:00Z',
                workduration: '8h',
                residentType: false,
                nation: '汉族',
                address: '北京市海淀区',
                email: 'zhangsan@example.com',
                gender: '男',
                type: true
            },
            user2: {
                id: '2',
                password: '567890',
                name: '李四',
                age: '30',
                position: '',
                phone: '13800138080',
                registerTime: '2023-01-01T10:00:00Z',
                onlineTime: '2023-01-01T10:30:00Z',
                loginTime: '2023-01-01T10:00:00Z',
                workStatus: true,
                offlineTime: '2023-01-01T18:00:00Z',
                workduration: '8h',
                residentType: false,
                nation: '汉族',
                address: '北京市海淀区',
                email: 'lisi@example.com',
                gender: '男',
                type: false
            },

        }
    },

    methods: {

        Login() {

            if (this.user.username == '' || this.user.password == '') {
                alert('账号或密码不能为空');
                return;
            }

            axios.post('http://localhost:8086/user/login', this.user).then(res => {
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

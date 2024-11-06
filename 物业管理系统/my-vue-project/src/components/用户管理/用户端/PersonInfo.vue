<template>
    <div class="content-wrapper">
        <div class="container-fluid">
            <!-- Breadcrumb-->
            <div class="row pt-2 pb-2">
                <div class="col-sm-12">
                    <h4 class="page-title">业主中心</h4>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">用户管理</li>
                        <li class="breadcrumb-item active" aria-current="page">业主中心</li>
                    </ol>

                    <div class="row">
                        <div class="col-lg-12">

                            <button type="button" @click="personinfo = true"
                                class="btn btn-outline-primary waves-effect waves-light m-1"><span>查看/修改个人信息</span>
                            </button>
                            <button type="button" class="btn btn-outline-success waves-effect waves-light m-1"
                                @click="paylist = true">
                                <span>查看缴费情况</span> </button>
                            <button type="button" class="btn btn-outline-success waves-effect waves-light m-1"
                                @click="userinformlist = true">
                                <span>查看通知</span> </button>
                            <button type="button" class="btn btn-outline-warning waves-effect waves-light m-1"
                                @click="usercarlist = true">
                                <span>查看车辆</span> </button>
                            <button type="button" class="btn btn-outline-primary waves-effect waves-light m-1"
                                @click="userparklist = true">
                                <span>查看车位</span> </button>
                            <button type="button" class="btn btn-outline-info waves-effect waves-light m-1"
                                @click="userhouselist = true">查看住房</button>
                            <button type="button" class="btn btn-outline-dark waves-effect waves-light m-1"
                                @click="pay = true">充值缴费</button>
                        </div>
                    </div>

                    <div class="card-body" v-show="personinfo">
                        <button type="button" class="btn btn-outline-primary waves-effect waves-light m-1"
                            @click="personinfo = false">收起面板</button>
                        <button type="button" class="btn btn-outline-info waves-effect waves-light m-1"
                            @click="updateperson">刷新</button>
                        <div class="card-content p-2">
                            <div class="text-center">
                                <img src="http://127.0.0.1:8083/images/logo-icon.png" alt="logo icon">
                            </div>
                            <div class="card-title text-uppercase text-center py-3">用户信息</div>
                            <form>
                                <div class="form-group">
                                    <label for="exampleInputName" class=""> 用户id </label>
                                    <div class="position-relative has-icon-right">
                                        <label class="">{{ user?.id }}</label>
                                        <div class="form-control-position">
                                            <i class="icon-user"></i>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputName" class="">姓名</label>
                                    <div class="position-relative has-icon-right">
                                        <label class="" v-show="!change">{{ user?.name }}</label>
                                        <input type="text" id="exampleInputName" class="form-control input-shadow"
                                            placeholder="输入你的姓名" v-show="change" v-if="user" v-model="user.name">
                                        <div class="form-control-position">
                                            <i class="icon-user"></i>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputName" class=""> 账户余额 </label>
                                    <div class="position-relative has-icon-right">
                                        <label class="">{{ user?.balance }}</label>
                                        <div class="form-control-position">
                                            <i class="icon-user"></i>
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label for="exampleInputEmailId" class="">邮箱</label>
                                    <div class="position-relative has-icon-right">
                                        <label class="" v-show="!change">{{ user?.email }}</label>
                                        <input type="email" id="exampleInputEmailId" class="form-control input-shadow"
                                            placeholder="输入你的邮箱账号" v-show="change" v-if="user" v-model="user.email">
                                        <div class="form-control-position">
                                            <i class="icon-envelope-open"></i>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPhoneId" class="">手机号</label>
                                    <div class="position-relative has-icon-right">
                                        <label class="" v-show="!change">{{ user?.phone }}</label>
                                        <input type="number" id="exampleInputPhoneId" class="form-control input-shadow"
                                            placeholder="输入你的手机号" v-show="change" v-if="user" v-model="user.phone">
                                        <div class="form-control-position">
                                            <i class="fa fa-phone"></i>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputPassword" class="">密码</label>
                                    <div class="position-relative has-icon-right">
                                        <label class="" v-show="!change">{{ user?.password }}</label>
                                        <input type="password" id="exampleInputPassword"
                                            class="form-control input-shadow" placeholder="输入密码" v-show="change"
                                            v-if="user" v-model="user.password">
                                        <div class="form-control-position">
                                            <i class="icon-lock"></i>
                                        </div>
                                    </div>
                                </div>


                                <button type="button"
                                    class="btn btn-primary shadow-primary btn-block waves-effect waves-light"
                                    v-show="!change" @click="change = true">修改个人信息</button>
                                <button type="button"
                                    class="btn btn-primary shadow-primary btn-block waves-effect waves-light"
                                    v-show="change" @click="submitpersoninfo">保存</button>
                            </form>
                        </div>
                    </div>
                    <div v-show="paylist" class="row">
                        <button type="button" class="btn btn-outline-primary waves-effect waves-light m-1"
                            @click="paylist = false">收起面板</button>
                        <PayList class="col-lg-12"></PayList>

                    </div>
                    <div v-show="userinformlist" class="row">
                        <button type="button" class="btn btn-outline-primary waves-effect waves-light m-1"
                            @click="userinformlist = false">收起面板</button>
                        <InformList class="col-lg-12"></InformList>

                    </div>
                    <div v-show="usercarlist" class="row">
                        <button type="button" class="btn btn-outline-primary waves-effect waves-light m-1"
                            @click="usercarlist = false">收起面板</button>
                        <UserCarList class="col-lg-12"></UserCarList>
                    </div>
                    <div v-show="userparklist" class="row">
                        <button type="button" class="btn btn-outline-primary waves-effect waves-light m-1"
                            @click="userparklist = false">收起面板</button>
                        <UserParkList class="col-lg-12"></UserParkList>
                    </div>
                    <div v-show="userhouselist" class="row">
                        <button type="button" class="btn btn-outline-primary waves-effect waves-light m-1"
                            @click="userhouselist = false">收起面板</button>

                        <UserHouseList class="col-lg-12"></UserHouseList>

                    </div>
                    <div v-show="pay" class="row">
                        <button type="button" class="btn btn-outline-primary waves-effect waves-light m-1"
                            @click="pay = false">收起面板</button>
                        <div class="card-body col-lg-12">
                            <form>
                                <div class="form-group">
                                    <label>充值金额</label>
                                    <input type="number" class="form-control" placeholder="请填写充值金额" v-if="user"
                                        v-model="money">
                                    <br>
                                    <button type="button" @click="paymoney"
                                        class="btn btn-primary shadow-primary px-5 col-lg-12">
                                        <i class="icon-lock"></i>提交</button>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>



            </div>
        </div>
    </div>




</template>
<script scoped>
import UserCarList from './UserCarList.vue';
import PayList from './PayList.vue';
import UserHouseList from './UserHouseList.vue';
import UserParkList from './UserParkList.vue';
import InformList from './InformList.vue';
import axios from 'axios';
export default {
    components: {
        UserCarList,
        PayList,
        UserHouseList,
        UserParkList,
        InformList

    },
    name: 'PersonInfo',
    methods: {
        switchToLogin() {
            this.$emit('switch-form', true);
        },
        Logout() {
            localStorage.removeItem('user');
            this.$router.push('/login');
        },
        paymoney() {
            this.user.balance += this.money;
            axios.put("http://localhost:8086/user/" + this.user.id, this.user).then(res => {
                if (res.data) {
                    alert("充值成功!");
                    this.pay = false;
                } else {
                    alert("充值失败.");

                }
            });
        },
        submitpersoninfo() {

            axios.put("http://localhost:8086/user/" + this.user.id, this.user).then(res => {
                if (res.data) {
                    alert("保存成功!");
                    this.change = false;
                } else {
                    alert("保存失败.");

                }
            });
        },
        updateperson() {
            console.log('User get from localStorage in PersonInfo:', this.user);
            this.user = JSON.parse(localStorage.getItem('user'));
        }
    },
    data() {
        return {
            user: null,
            change: false,
            personinfo: false,
            paylist: false,
            usercarlist: false,
            userparklist: false,
            userhouselist: false,
            userinformlist: false,
            pay: false,
            money: 0
        }
    },
    mounted() {
        this.user = JSON.parse(localStorage.getItem('user'));
        console.log('User get from localStorage in PersonInfo:', this.user);
    },


}
</script>
<style scoped></style>
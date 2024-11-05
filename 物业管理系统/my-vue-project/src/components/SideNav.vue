<template>
    <div id="sidebar-wrapper" data-simplebar="init" data-simplebar-auto-hide="true">

        <div class="brand-logo">
            <a href="index.html">
                <img src="http://127.0.0.1:8083/images/logo-icon.png" class="logo-icon" alt="logo icon">

                <h5 class="logo-text" v-if="user?.type">管理员</h5>

                <h5 class="logo-text" v-else>普通用户</h5>

            </a>
        </div>
        <ul class="sidebar-menu do-nicescrol">
            <li class="sidebar-header">导航栏</li>

            <li v-if="user?.type">
                <a href="#" class="waves-effect">
                    <i class="zmdi zmdi-layers"></i>
                    <span>房屋管理</span> <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="sidebar-submenu">
                    <li><a href="#" @click="selectOption('房屋信息管理')"> <i class=" zmdi zmdi-star-outline"></i>
                            房屋信息管理</a></li>
                    <li><a href="#" @click="selectOption('合同与租赁管理')"><i class="zmdi zmdi-star-outline"></i>
                            合同与租赁管理</a>
                        <a href="#" @click="selectOption('住房历史管理')"><i class="zmdi zmdi-star-outline"></i>
                            住房历史管理</a>
                    </li>
                </ul>

            </li>

            <li v-if="user?.type">
                <a href="#" class="waves-effect">
                    <i class="zmdi zmdi-chart"></i><span>收费管理</span>
                    <i class="fa fa-angle-left float-right"></i>
                </a>
                <ul class="sidebar-submenu">
                    <li><a href="#" @click="selectOption('收费执行')"><i class="zmdi zmdi-star-outline"></i>收费执行</a>
                    </li>


                </ul>
            </li>
            <li v-if="user?.type">
                <a href="#" class="waves-effect">
                    <i class="zmdi zmdi-cloud-done"></i><span>车辆管理</span><i class="fa fa-angle-left float-right"></i>
                </a>
                <ul class="sidebar-submenu">
                    <li><a href="#" @click="selectOption('车辆管理')"><i class="zmdi zmdi-star-outline"></i>车辆管理</a>
                    </li>
                    <li><a href="#" @click="selectOption('车辆管理')"><i class="zmdi zmdi-star-outline"></i>车辆管理</a>
                    </li>

                </ul>
            </li>
            <li>
                <a href="#" class="waves-effect">
                    <i class="zmdi zmdi-gamepad"></i><span>物业报修</span>
                    <i class="fa fa-angle-left float-right"></i>
                </a>
                <ul class="sidebar-submenu">
                    <li v-if="user?.type"><a href="#" @click="selectOption('报修管理')"><i
                                class="zmdi zmdi-star-outline"></i>
                            报修管理</a>
                    </li>
                    <li v-if="!user?.type"><a href="#" @click="selectOption('我的报修')"><i
                                class="zmdi zmdi-star-outline"></i>
                            我的报修</a>
                    </li>
                    <li v-if="user?.type"><a href="#" @click="selectOption('数据统计')"><i
                                class="zmdi zmdi-star-outline"></i>
                            数据统计</a>
                    </li>

                </ul>

            </li>



            <li>
                <a href="#" class="waves-effect">
                    <i class="zmdi zmdi-chart"></i><span>用户管理</span>
                    <i class="fa fa-angle-left float-right"></i>
                </a>
                <ul class="sidebar-submenu">
                    <li v-if="user?.type"><a href="#" @click="selectOption('业主信息管理')"><i
                                class="zmdi zmdi-star-outline"></i>
                            业主信息管理</a></li>

                    <li v-if="!user?.type"><a href="#" @click="selectOption('业主中心')"><i
                                class="zmdi zmdi-star-outline"></i>
                            业主中心</a></li>

                </ul>
            </li>
            <li v-if="user?.type">
                <a href="#" class="waves-effect">
                    <i class="zmdi zmdi-card-travel"></i><span>访客管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="sidebar-submenu">
                    <li><a href="#" @click="selectOption('访客登记')"><i class="zmdi zmdi-star-outline"></i>
                            访客登记</a>
                    </li>
                    <li><a href="#" @click="selectOption('访客离开与历史记录')"><i class="zmdi zmdi-star-outline"></i>
                            访客离开与历史记录</a>
                    </li>
                    <li v-if="user?.type" @click="selectOption('处理结果反馈')"><a href="#"><i
                                class="zmdi zmdi-star-outline"></i> 处理结果反馈</a>
                    </li>

                </ul>

            </li>
            <li>
                <a href="#" class="waves-effect">
                    <i class="zmdi zmdi-gamepad"></i><span>投诉建议</span><i class="fa fa-angle-left float-right"></i>
                </a>
                <ul class="sidebar-submenu">
                    <li v-if="!user?.type"><a href="#" @click="selectOption('投诉与建议提交')"><i
                                class="zmdi zmdi-star-outline"></i>投诉与建议提交</a>
                    </li>
                    <li v-if="user?.type"><a href="#" @click="selectOption('投诉与建议处理')"><i
                                class="zmdi zmdi-star-outline"></i>投诉与建议处理</a>
                    </li>
                    <li v-if="!user?.type"><a href="#" @click="selectOption('处理结果反馈')"><i
                                class="zmdi zmdi-star-outline"></i>处理结果反馈</a>
                    </li>
                </ul>

            </li>




        </ul>

    </div>

</template>

<script scoped>

export default {
    components: {

    },
    data() {
        return {
            user: null
        }
    },
    beforeMount() {
        // 读取 localStorage
        this.user = JSON.parse(localStorage.getItem('user'));

    },
    methods: {

        selectOption(option) {
            this.$emit('select-option', option);
        },

    }
}
</script>

<style scoped></style>
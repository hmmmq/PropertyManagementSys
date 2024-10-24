<template>
    <div id="wrapper">
        <SideNav @select-option="handleOptionSelect" />
        <NavHeader @select-option="handleOptionSelect" />
        <WelcomePanel v-show="currentComponent === 'WelcomePanel'"></WelcomePanel>
        <UserFixList v-if="currentComponent === 'UserFixList'"></UserFixList>
        <PersonInfo v-if="currentComponent === 'PersonInfo'"></PersonInfo>
        <HouseList v-if="currentComponent === 'HouseList'"></HouseList>
        <FeeList v-if="currentComponent === 'FeeList'"></FeeList>
        <CarList v-if="currentComponent === 'CarList'"></CarList>
        <FixList v-if="currentComponent === 'FixList'"></FixList>
        <UserList v-if="currentComponent === 'UserList'"></UserList>

    </div>

</template>
<script scoped>
import SideNav from '@/components/SideNav.vue';
import NavHeader from '@/components/NavHeader.vue';
import WelcomePanel from '@/components/WelcomePanel.vue';
import PersonInfo from '@/components/用户管理/PersonInfo.vue';
import HouseList from '@/components/住房管理/HouseList.vue';
import FeeList from '@/components/收费管理/FeeList.vue';
import CarList from '@/components/车位管理/CarList.vue';
import FixList from '@/components/物业报修/FixList.vue';
import UserList from '@/components/用户管理/UserList.vue';
import UserFixList from '@/components/物业报修/UserFixList.vue';
export default {
    components: {
        SideNav,
        NavHeader,
        WelcomePanel,
        UserList,
        PersonInfo,
        HouseList,
        FeeList,
        CarList,
        // UserCarList,
        FixList,
        UserFixList
    },
    data() {
        return {

            currentComponent: 'WelcomePanel'
        }
    },
    name: 'UserPage',
    methods: {
        handleOptionSelect(option) {
            switch (option) {
                case '业主信息管理':
                    this.currentComponent = 'UserList';
                    break;
                case '业主中心':
                    this.currentComponent = 'PersonInfo';
                    break;
                case '房屋信息管理':
                    this.currentComponent = 'HouseList';
                    break;
                case '收费执行':
                    this.currentComponent = 'FeeList';
                    break;
                case '查看账单':
                    this.currentComponent = 'PayList';
                    break;
                case '车辆管理':
                    this.currentComponent = 'CarList';
                    break;
                case '我的车辆':
                    this.currentComponent = 'UserCarList';
                    break;
                case '报修管理':
                    this.currentComponent = 'FixList';
                    break;
                case '我的报修':
                    this.currentComponent = 'UserFixList';
                    break;
                default:

            }
        }
    },


}
</script>
<style scoped></style>
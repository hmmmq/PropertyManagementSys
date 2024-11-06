<template>
    <div id="wrapper">
        <SideNav @select-option="handleOptionSelect" />
        <NavHeader @select-option="handleOptionSelect" />
        <WelcomePanel v-show="currentComponent === 'WelcomePanel'"></WelcomePanel>
        <UserFixList v-show="currentComponent === 'UserFixList'"></UserFixList>
        <PersonInfo v-show="currentComponent === 'PersonInfo'"></PersonInfo>
        <HouseList v-show="currentComponent === 'HouseList'"></HouseList>
        <FeeList v-show="currentComponent === 'FeeList'"></FeeList>
        <CarList v-show="currentComponent === 'CarList'"></CarList>
        <FixList v-show="currentComponent === 'FixList'"></FixList>
        <UserList v-show="currentComponent === 'UserList'"></UserList>
        <AdminList v-show="currentComponent === 'AdminList'"></AdminList>
        <ParkList v-show="currentComponent === 'ParkList'"></ParkList>
        <ContractList v-show="currentComponent === 'ContractList'"></ContractList>
        <InformList v-show="currentComponent === 'InformList'"></InformList>
        <HistoryHouseList v-show="currentComponent === 'HistoryHouseList'"></HistoryHouseList>
        <HistoryParkList v-show="currentComponent === 'HistoryParkList'"></HistoryParkList>
    </div>

</template>
<script scoped>
import SideNav from '@/components/SideNav.vue';
import NavHeader from '@/components/NavHeader.vue';
import WelcomePanel from '@/components/WelcomePanel.vue';
import PersonInfo from '@/components/用户管理/用户端/PersonInfo.vue';
import HouseList from '@/components/房屋管理/HouseList.vue';
import FeeList from '@/components/收费管理/FeeList.vue';
import CarList from '@/components/车辆管理/CarList.vue';
import FixList from '@/components/物业报修/FixList.vue';
import UserList from '@/components/用户管理/管理员端/UserList.vue';
import UserFixList from '@/components/物业报修/UserFixList.vue';
import AdminList from '@/components/管理员管理/AdminList.vue';
import ParkList from '@/components/车位管理/ParkList.vue';
import ContractList from '@/components/合同管理/ContractList.vue';
import InformList from '@/components/通知管理/InformList.vue';
import HistoryHouseList from '@/components/住房历史管理/HouseList.vue';
import HistoryParkList from '@/components/车位历史记录/ParkList.vue';
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
        FixList,
        UserFixList,
        AdminList,
        ParkList,
        ContractList,
        InformList,
        HistoryHouseList,
        HistoryParkList
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
                case '管理员信息管理':
                    this.currentComponent = 'AdminList';
                    break;
                case '通知管理':
                    this.currentComponent = 'InformList';
                    break;
                case '业主中心':
                    this.currentComponent = 'PersonInfo';
                    break;
                case '房屋信息管理':
                    this.currentComponent = 'HouseList';
                    break;
                case '住房历史管理':
                    this.currentComponent = 'HistoryHouseList';
                    break;
                case '合同与租赁管理':
                    this.currentComponent = 'ContractList';
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
                case '车位管理':
                    this.currentComponent = 'ParkList';
                    break;
                case '车位历史管理':
                    this.currentComponent = 'HistoryParkList';
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
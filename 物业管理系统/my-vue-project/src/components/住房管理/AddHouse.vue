<template>
    <div class="content-wrapper">
        <div class="container-fluid">
            <!-- Breadcrumb-->
            <div class="row pt-2 pb-2">
                <div class="col-sm-9">
                    <h4 class="page-title">住房管理</h4>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">房屋信息管理</li>
                        <li class="breadcrumb-item active" aria-current="page">新增房屋信息</li>
                    </ol>
                </div>
            </div>
            <!-- End Breadcrumb-->
            <div class="row justify-content-center">
                <div class="card col-lg-12">
                    <div class="card-body">
                        <div class="card-title text-primary">新增房屋</div>
                        <hr>
                        <form>
                            <div class="form-group">
                                <label>房屋ID</label>
                                <input type="number" class="form-control" placeholder="请填写房屋ID" v-model="house.id">
                            </div>
                            <div class="form-group">
                                <label>房主ID</label>
                                <input type="number" class="form-control" placeholder="请填写房主ID" v-model="house.ownerid">
                            </div>
                            <div class="form-group">
                                <label>房屋类型</label>
                                <select class="form-control" v-model="house.type">
                                    <option value="普通住宅">普通住宅</option>
                                    <option value="商业用房">商业用房</option>
                                    <option value="工业用房">工业用房</option>
                                    <option value="其他">其他</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>房屋面积(平方米)</label>
                                <input type="number" class="form-control" placeholder="填写房屋面积" v-model="house.area">
                            </div>
                            <div class="form-group">
                                <label>房屋住址</label>
                                <input type="text" class="form-control" placeholder="填写房屋住址" v-model="house.address">
                            </div>

                            <div class="form-group">
                                <label>房屋状态</label>
                                <select class="form-control" v-model="house.status">
                                    <option value="空闲">空闲</option>
                                    <option value="已出售">已出售</option>
                                    <option value="已租赁">已租赁</option>
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
            house: {
                id: null,
                ownerid: null,
                type: '',
                area: '',
                address: '',
                status: ''
            },
            URL: 'http://localhost:8086/house/'
        }
    },
    methods: {
        checknullvalue() {
            if (this.house.id == '' || this.house.area == ''
                || this.house.address == '' || this.house.type == ''
                || this.house.status == '') {
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

            axios.post(this.URL, this.house).then(res => {
                if (res.data != '' && res.data) {
                    alert('新增房屋成功');
                    this.house = {
                        id: null,
                        ownerid: null,
                        type: '',
                        area: '',
                        address: '',
                        status: ''
                    };
                } else {
                    alert('新增房屋失败,请检查房屋ID是否已存在');
                }
            }).catch(err => {
                console.log("submit err");
                console.error(err);
            });
        }
    }
}
</script>
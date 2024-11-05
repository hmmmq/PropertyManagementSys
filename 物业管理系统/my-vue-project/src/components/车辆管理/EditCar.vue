<template>
    <div class="content-wrapper">
        <div class="container-fluid">
            <!-- Breadcrumb-->
            <div class="row pt-2 pb-2">
                <div class="col-sm-9">
                    <h4 class="page-title">车辆管理</h4>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">车辆管理</li>
                        <li class="breadcrumb-item active" aria-current="page">车主登记</li>
                    </ol>
                </div>
            </div>
            <button type="button" class="btn btn-outline-info waves-effect waves-light m-1"
                @click="cancel">取消修改</button>
            <!-- End Breadcrumb-->
            <div class="row justify-content-center">
                <div class="card col-lg-12">
                    <div class="card-body">
                        <div class="card-title text-primary">车主登记</div>
                        <hr>
                        <form>
                            <div class="form-group">
                                <label>车辆ID</label>
                                <input type="number" class="form-control" placeholder="请填写车辆ID" v-model="car.id"
                                    disabled>
                            </div>
                            <div class="form-group">
                                <label>车辆用户ID</label>
                                <input type="text" class="form-control" placeholder="车辆用户ID" v-model="car.userid">
                            </div>
                            <div class="form-group">
                                <label>车牌号</label>
                                <input type="text" class="form-control" placeholder="车牌号" v-model="car.number">
                            </div>

                            <div class="form-group">
                                <label>车辆类型</label>
                                <select class="form-control" v-model="car.type">
                                    <option value="小型车">小型车</option>
                                    <option value="中型车">中型车</option>
                                    <option value="大型车">大型车</option>
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
    props: {
        initialcar: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            car: {
                id: this.initialcar.id || '',
                userid: this.initialcar.userid || '',
                type: this.initialcar.type || '',
                number: this.initialcar.number || ''
            },
            URL: 'http://localhost:8086/car/'
        }
    },
    methods: {
        cancel() {
            this.$emit('data-back-car', true);
        },
        checknullvalue() {
            if (this.car.id == '' || this.car.userid == '' || this.car.type == '' || this.car.number == '') {
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
            this.URL = this.URL + this.car.id;

            axios.put(this.URL, this.car).then(res => {

                if (res.data != '' && res.data != false) {
                    alert('修改车辆成功');
                    this.car = {
                        id: '',
                        userid: '',
                        type: '',
                        number: ''
                    };
                    this.$emit('data-back-car', true);

                } else {
                    alert('修改车辆失败,请检查车辆ID是否已存在');
                }
            }).catch(err => {
                console.error(err);
            });

        }
    }
}
</script>
<template>
    <div class="content-wrapper">
        <div class="container-fluid">
            <!-- Breadcrumb-->
            <div class="row pt-2 pb-2">
                <div class="col-sm-9">
                    <h4 class="page-title">车位管理</h4>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">车位管理</li>
                        <li class="breadcrumb-item active" aria-current="page">车主登记</li>
                    </ol>
                </div>
            </div>
            <button type="button" class="btn btn-outline-info waves-effect waves-light m-1"
                @click="cancel">取消修改</button>
            <!-- End Breadcrumb-->
            <div class="row justify-content-center">
                <div class="parkd col-lg-12">
                    <div class="parkd-body">
                        <div class="parkd-title text-primary">车主登记</div>
                        <hr>
                        <form>
                            <div class="form-group">
                                <label>车位ID</label>
                                <input type="number" class="form-control" placeholder="请填写车位ID" v-model="park.id"
                                    disabled>
                            </div>
                            <div class="form-group">
                                <label>车位车主ID</label>
                                <input type="number" class="form-control" placeholder="请填写车位车主ID" v-model="park.userid">
                            </div>
                            <div class="form-group">
                                <label>车位车主姓名</label>
                                <input type="text" class="form-control" placeholder="请填写车位车主姓名" v-model="park.username">
                            </div>
                            <div class="form-group">
                                <label>车位车主车辆ID</label>
                                <input type="text" class="form-control" placeholder="请填写车位车主车牌号" v-model="park.carid">
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
        initialpark: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            park: {
                id: this.initialpark.id || '',
                carid: this.initialpark.carid || '',
                createTime: this.initialpark.createTime || '',
                username: this.username || '',
                userid: this.userid || '',
                status: this.status || '未被预约'
            },
            URL: 'http://localhost:8086/park/'
        }
    },
    methods: {
        cancel() {
            this.$emit('data-back-park', true);
        },
        checknullvalue() {
            if (this.park.id == '') {
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
            this.URL = this.URL + this.park.id;

            axios.put(this.URL, this.park).then(res => {

                if (res.data != '' && res.data != false) {
                    alert('修改车位成功');
                    this.park = {
                        id: '',
                        userid: '',
                        type: '',
                        number: ''
                    };
                    this.$emit('data-back-park', true);

                } else {
                    alert('修改车位失败,请检查车位ID是否已存在');
                }
            }).catch(err => {
                console.error(err);
            });

        }
    }
}
</script>
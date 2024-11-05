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
            <!-- End Breadcrumb-->
            <div class="row justify-content-center">
                <div class="parkd col-lg-12">
                    <div class="parkd-body">
                        <div class="parkd-title text-primary">车主登记</div>
                        <hr>
                        <form>
                            <div class="form-group">
                                <label>车位ID</label>
                                <input type="number" class="form-control" placeholder="请填写车位ID" v-model="park.id">
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
            park: {
                id: '',
                carid: '',
                reserveTime: '',
                username: '',
                userid: '',
                status: '未被预约'
            },
            URL: 'http://localhost:8086/park/'
        }
    },
    methods: {
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

            axios.post(this.URL, this.park).then(res => {

                if (res.data != '' && res.data != false) {
                    alert('新增车位成功');
                    this.park = {
                        id: '',
                        carid: '',
                        reserveTime: '',
                        username: '',
                        userid: '',
                        status: '未被预约'
                    };
                } else {
                    alert('新增车位失败,请检查车位ID是否已存在');
                }
            }).catch(err => {
                console.error(err);
            });

        }
    }
}
</script>
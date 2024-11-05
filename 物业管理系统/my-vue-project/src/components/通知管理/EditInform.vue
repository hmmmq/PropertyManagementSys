<template>
    <div class="content-wrapper">
        <div class="container-fluid">
            <!-- Breadcrumb-->
            <div class="row pt-2 pb-2">
                <div class="col-sm-9">
                    <h4 class="page-title">通知管理</h4>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">通知管理</li>
                        <li class="breadcrumb-item active" aria-current="page">编辑通知</li>
                    </ol>
                </div>
            </div>
            <button type="button" class="btn btn-outline-info waves-effect waves-light m-1"
                @click="cancel">取消修改</button>
            <!-- End Breadcrumb-->
            <div class="row justify-content-center">
                <div class="informd col-lg-12">
                    <div class="informd-body">
                        <div class="informd-title text-primary">编辑通知</div>
                        <hr>
                        <form>
                            <div class="form-group">
                                <label>通知ID</label>
                                <input type="number" class="form-control" placeholder="请填写通知ID" v-model="inform.id"
                                    disabled>
                            </div>
                            <!-- name -->
                            <div class="form-group">
                                <label>通知名称</label>
                                <input type="text" class="form-control" placeholder="通知名称" v-model="inform.name">
                            </div>



                            <div class="form-group">
                                <label>签署通知用户ID</label>
                                <input type="number" class="form-control" placeholder="通知用户ID"
                                    v-model="inform.signUserid">
                            </div>


                            <div class="form-group">
                                <label>通知类型</label>
                                <select class="form-control" v-model="inform.type">
                                    <option value="租赁通知">租赁通知</option>
                                    <option value="销售通知">销售通知</option>
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
        initialinform: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            inform: {
                id: this.initialinform.id || '',
                userid: this.initialinform.userid || '',
                message: this.initialinform.message || '',
                informTime: this.initialinform.informTime || '',
                status: this.initialinform.status || ''
            },
            URL: 'http://localhost:8086/inform/'
        }
    },
    methods: {
        cancel() {
            this.$emit('data-back-inform', true);
        },
        checknullvalue() {
            if (this.inform.id == '' || this.inform.userid == '' || this.inform.type == '' || this.inform.number == '') {
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
            this.URL = this.URL + this.inform.id;

            axios.put(this.URL, this.inform).then(res => {

                if (res.data != '' && res.data != false) {
                    alert('修改通知成功');
                    this.inform = {
                        id: '',
                        userid: '',
                        type: '',
                        number: ''
                    };
                    this.$emit('data-back-inform', true);

                } else {
                    alert('修改通知失败,请检查通知ID是否已存在');
                }
            }).catch(err => {
                console.error(err);
            });

        }
    }
}
</script>
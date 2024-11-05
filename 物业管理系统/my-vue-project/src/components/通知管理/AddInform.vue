<template>
    <div class="content-wrapper">
        <div class="container-fluid">
            <!-- Breadcrumb-->
            <div class="row pt-2 pb-2">
                <div class="col-sm-9">
                    <h4 class="page-title">通知管理</h4>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">通知管理</li>
                        <li class="breadcrumb-item active" aria-current="page">新增通知</li>
                    </ol>
                </div>
            </div>
            <!-- End Breadcrumb-->
            <div class="row justify-content-center">
                <div class="informd col-lg-12">
                    <div class="informd-body">
                        <div class="informd-title text-primary">新增通知</div>
                        <hr>
                        <form>
                            <div class="form-group">
                                <label>通知ID</label>
                                <input type="number" class="form-control" placeholder="请填写通知ID" v-model="inform.id">
                            </div>
                            <div class="form-group">
                                <label>通知用户ID</label>
                                <input type="text" class="form-control" placeholder="通知用户ID" v-model="inform.userid">
                            </div>
                            <!-- //消息 -->
                            <div class="form-group">
                                <label>通知消息</label>
                                <input type="text" class="form-control" placeholder="通知消息" v-model="inform.message">
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
            inform: {
                id: '',
                userid: '',
                message: '',
                informTime: '',
                status: '未读'
            },
            URL: 'http://localhost:8086/inform/'
        }
    },
    methods: {
        checknullvalue() {
            if (this.inform.id == '' || this.inform.userid == '' || this.inform.message == '') {
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

            axios.post(this.URL, this.inform).then(res => {

                if (res.data != '' && res.data != false) {
                    alert('新增通知成功');
                    this.inform = {
                        id: '',
                        userid: '',
                        message: '',
                        informTime: '',
                        status: '未读'
                    };
                } else {
                    alert('新增通知失败,请检查通知ID是否已存在');
                }
            }).catch(err => {
                console.error(err);
            });

        }
    }
}
</script>
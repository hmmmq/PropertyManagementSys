<template>
    <div class="content-wrapper">
        <div class="container-fluid">
            <!-- Breadcrumb-->
            <div class="row pt-2 pb-2">
                <div class="col-sm-9">
                    <h4 class="page-title">车辆管理</h4>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">车位管理</li>
                        <li class="breadcrumb-item active" aria-current="page">车辆管理</li>
                    </ol>
                </div>

            </div>
            <!-- End Breadcrumb-->
            <button type="button" class="btn btn-outline-success waves-effect waves-light m-1"
                @click="addcar = true">登记车辆</button>
            <button type="button" class="btn btn-outline-info waves-effect waves-light m-1"
                @click="updatelist">刷新列表</button>
            <car-edit :initialcar="initialcar" v-if="this.initialcar.id != ''"
                @data-back-car="handleDataBack"></car-edit>
            <div class="row">
                <div class="col-lg-10" style="align-items: center;" v-show="addcar">
                    <button type="button" class="btn btn-outline-success waves-effect waves-light m-1"
                        @click="addcar = false">收起面板</button>
                    <AddUserCar></AddUserCar>
                </div>
            </div>



            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-header"><i class="fa fa-table"></i>车辆列表</div>
                        <div class="card-body">

                            <div class="table-responsive">
                                <table id="example114" class="table table-bordered">
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!-- End Row-->

        </div>
        <!-- End container-fluid-->

    </div>
</template>
<script scoped>
import axios from 'axios';
import $ from 'jquery';
import 'datatables.net-bs4';
import 'datatables.net-buttons-bs4';
import 'datatables.net-buttons/js/buttons.html5';
import 'datatables.net-buttons/js/buttons.print';
import 'datatables.net-buttons/js/buttons.colVis';
import 'jszip';
import pdfMake from 'pdfmake-support-chinese-fonts/pdfmake.min';
import pdfFonts from 'pdfmake-support-chinese-fonts/vfs_fonts';
import AddUserCar from './AddUserCar.vue';
import CarEdit from './EditCar.vue';
pdfMake.vfs = pdfFonts.pdfMake.vfs;
pdfMake.fonts = {
    Roboto: {
        normal: 'Roboto-Regular.ttf',
        bold: 'Roboto-Regular.ttf',
        italics: 'Roboto-Regular.ttf',
        bolditalics: 'Roboto-Regular.ttf'
    },
    fangzhen: {
        normal: 'fzhei-jt.TTF',
        bold: 'fzhei-jt.TTF',
        italics: 'fzhei-jt.TTF',
        bolditalics: 'fzhei-jt.TTF'
    }
};

export default {
    components: {
        AddUserCar,
        CarEdit
    },
    data() {
        return {
            user: null,
            addcar: false,
            addacar: false,
            carlist: [], // 初始化为空数组
            ready: false, // 初始化为 false,
            update: false,
            initialcar: {
                id: '',
                userid: '',
                type: '',
                number: ''
            }
        }
    },

    watch: {
    },
    methods: {
        handleDataBack(data) {
            console.log('回传的数据:', data);
            // 处理回传的数据
            if (data) {
                this.initialcar.id = '';
            }
        },
        async updatelist() {
            await this.destoryDataTable();
            this.initializeDataTable();

        },
        fetchData() {
            try {
                const res = axios.get('http://localhost:8086/car/');
                if (res.status === 200) {
                    console.log('http://localhost:8086/car/');
                    console.log('this.carlist = res.data;');
                }
            } catch (err) {
                console.error(err);
            }
        },
        convertTo2DArray(data) {
            return data.map((item, index) => [
                index + 1, // 添加索引列
                item.id,
                item.userid,
                item.type,
                item.number,
                item.id// 占位符，用于操作列


            ]);
        },
        async initializeDataTable() {
            console.log("initializeDataTable");
            var carlist2d = null;
            try {
                if (!$.fn.DataTable.isDataTable('#example114')) {


                    try {
                        const promise = await axios.get('http://localhost:8086/car/');
                        if (promise.status === 200) {
                            console.log(promise.data);
                            carlist2d = this.convertTo2DArray(promise.data);
                        } else {
                            console.log(promise);
                            return;
                        }
                    } catch (err) {
                        console.error(err);
                        return;
                    }

                    this.$nextTick(() => {
                        console.log(" var table = $('#example114').DataTable({");
                        var table = $('#example114').DataTable({
                            dom: '<"top"l<"row"<"col-sm-6 text-left"f><"col-sm-6 text-right"B>>rt<"bottom"<"row"<"col-sm-12 dt-info-container"i>><"row"<"col-sm-12 dt-paging-container"p>>><"clear">',
                            buttons: [
                                'copy', 'csv', 'excel', {
                                    extend: 'pdfHtml5',
                                    text: 'PDF',
                                    customize: function (doc) {
                                        doc.defaultStyle = {
                                            font: 'fangzhen' // 使用支持中文的字体
                                        };
                                    }
                                }, 'print', 'colvis'
                            ],
                            data: carlist2d,
                            columns: [
                                { title: '序号' },
                                { title: '车辆ID' },
                                { title: '车辆用户ID' },
                                { title: '车辆类型' },
                                { title: '车牌号' },
                                {
                                    title: '操作',
                                    render: function (data) {
                                        return `
                                         <button class="edit-btn btn btn-outline-primary buttons-copy buttons-html5" value=${data}>编辑</button>
                                        <button class="delete-btn btn btn-outline-primary buttons-copy buttons-html5" value=${data}>删除</button>
                                        `;
                                    }
                                }
                            ],
                            language: {
                                search: "搜索:",
                                lengthMenu: "每页显示 _MENU_ 条记录",
                                info: "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                                paginate: {
                                    first: "首页",
                                    last: "末页",
                                    next: "下一页",
                                    previous: "上一页"
                                }
                            }
                        });

                        // 绑定编辑和删除按钮的事件
                        $('#example114 tbody').on('click', '.edit-btn', (event) => {
                            var data = $(event.currentTarget).val();
                            console.log('编辑数据:', data);
                            console.log('数据:', this.initialcar);
                            axios.get('http://localhost:8086/car/' + data).then(res => {
                                this.initialcar = res.data;
                            });

                        });
                        $('#example114 tbody').off('click', '.delete-btn').on('click', '.delete-btn', (event) => {
                            axios.delete('http://localhost:8086/car/' + $(event.currentTarget).val()).then(res => {
                                if (res.data) {
                                    console.log('删除成功');
                                    alert('删除成功');
                                } else {
                                    console.error('删除失败');
                                }
                            });
                        });
                        console.log("table.buttons().container().appendTo(#example_wrapper.col-md-6:eq(0));");
                        table
                            .buttons()
                            .container()
                            .appendTo("#example_wrapper .col-md-6:eq(0)");

                        // 为所有按钮元素添加样式
                        console.log("const buttons = this.$el.querySelectorAll('.table-responsive button');");
                        const buttons = this.$el.querySelectorAll('.table-responsive button');
                        buttons.forEach(button => {
                            button.classList.add('btn', 'btn-outline-primary', 'buttons-copy', 'buttons-html5');
                        });
                        console.log("$('.dt-search').css('text-align', 'left'); this.$el.querySelector('.dt-length').classList.add('col-md-1');$('.dt-length').css('padding-left', '0');$('.text-right').css('padding-top', '20px');");
                        // 使用jQuery动态调整底部元素的样式
                        $('.dt-info-container').css({
                            display: 'flex',
                            justifyContent: 'flex-end',
                            alignItems: 'center',
                            paddingRight: '20px'
                        });
                        $('.dt-paging-container').css({
                            display: 'flex',
                            justifyContent: 'flex-end',
                            alignItems: 'center',
                            paddingRight: '20px'
                        });
                        console.log("-----------initializeDataTable END-----------");
                    });
                }
            } catch (error) {
                console.error('回调函数内部发生错误:', error);
            }

        },
        async destoryDataTable() {

            this.$nextTick(() => {
                if ($.fn.DataTable.isDataTable('#example114')) {
                    console.log("destoryDataTable");
                    $('#example114').DataTable().destroy();
                }
            });
        }


    }
}
</script>
<style scoped>
/* 样式 */
.dt-search {
    text-align: left;
}

.bottom {
    display: flex;
    justify-content: flex-end;
    align-items: center;
}

.dt-info {
    margin-right: 20px;
    /* 可根据需要调整间距 */
}
</style>
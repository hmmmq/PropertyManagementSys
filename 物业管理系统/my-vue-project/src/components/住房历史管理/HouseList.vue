<template>
    <div class="content-wrapper">
        <div class="container-fluid">
            <!-- Breadcrumb-->
            <div class="row pt-2 pb-2">
                <div class="col-sm-9">
                    <h4 class="page-title">房屋管理</h4>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">房屋管理</li>
                        <li class="breadcrumb-item active" aria-current="page">房屋管理</li>
                    </ol>
                </div>
            </div>
            <!-- End Breadcrumb-->

            <button type="button" class="btn btn-outline-info waves-effect waves-light m-1"
                @click="updatelist">刷新列表</button>
            <edit-house :initialhouse="initialhouse" v-if="this.initialhouse.id != ''"
                @data-back-house="handleDataBack"></edit-house>

            <div class="row">
                <div class="col-lg-12">
                    <div class="card">

                        <div class="card-header"><i class="fa fa-table"></i>房屋列表</div>
                        <div class="card-body">

                            <div class="table-responsive">
                                <div id="example_wrapper"></div>
                                <table id="example1111" class="table table-bordered">

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
import EditHouse from './EditHouse.vue';
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

        EditHouse
    },
    data() {
        return {
            user: null,
            addhouse: false,
            addahouse: false,
            houselist: [], // 初始化为空数组
            ready: false, // 初始化为 false,
            update: false,
            initialhouse: {
                id: '',
                ownerid: '',
                type: '',
                area: '',
                address: '',
                status: '',
                enterTime: '',
                leaveTime: ''
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
                this.initialhouse.id = '';
            }
        },
        async updatelist() {
            await this.destoryDataTable();
            this.initializeDataTable();

        },
        fetchData() {
            try {
                const res = axios.get('http://localhost:8086/house/');
                if (res.status === 200) {
                    console.log('http://localhost:8086/house/');
                    console.log('this.houselist = res.data;');
                }
            } catch (err) {
                console.error(err);
            }
        },
        convertTo2DArray(data) {
            return data.map((item, index) => [
                index + 1, // 添加索引列
                item.id,
                item.ownerid,
                item.type,
                item.area,
                item.address,
                item.status,
                item.enterTime,
                item.leaveTime,
                item.id// 占位符，用于操作列


            ]);
        },
        async initializeDataTable() {
            console.log("initializeDataTable");
            var houselist2d = null;
            try {
                if (!$.fn.DataTable.isDataTable('#example1111')) {


                    try {
                        const promise = await axios.get('http://localhost:8086/house/');
                        if (promise.status === 200) {
                            console.log(promise.data);
                            houselist2d = this.convertTo2DArray(promise.data);
                        } else {
                            console.log(promise);
                            return;
                        }
                    } catch (err) {
                        console.error(err);
                        return;
                    }

                    this.$nextTick(() => {
                        console.log(" var table = $('#example1111').DataTable({");
                        var table = $('#example1111').DataTable({
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
                            data: houselist2d,
                            columns: [
                                { title: '序号' },
                                { title: '房屋ID' },
                                { title: '房主ID' },
                                { title: '类型' },
                                { title: '面积' },
                                { title: '地址' },
                                { title: '状态' },
                                { title: '入住时间' },
                                { title: '离开时间' },
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
                        $('#example1111 tbody').on('click', '.edit-btn', (event) => {
                            var data = $(event.currentTarget).val();
                            console.log('编辑数据:', data);
                            console.log('数据:', this.initialhouse);
                            axios.get('http://localhost:8086/house/' + data).then(res => {
                                this.initialhouse = res.data;
                            });

                        });

                        $('#example1111 tbody').off('click', '.delete-btn').on('click', '.delete-btn', (event) => {
                            axios.delete('http://localhost:8086/house/' + $(event.currentTarget).val()).then(res => {
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
                if ($.fn.DataTable.isDataTable('#example1111')) {
                    console.log("destoryDataTable");
                    $('#example1111').DataTable().destroy();
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

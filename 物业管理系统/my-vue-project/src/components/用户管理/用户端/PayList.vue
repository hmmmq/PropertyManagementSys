<template>
    <div class="content-wrapper">
        <div class="container-fluid">
            <!-- Breadcrumb-->
            <div class="row pt-2 pb-2">
                <div class="col-sm-9">
                    <h4 class="page-title">收费执行</h4>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">收费管理</li>
                        <li class="breadcrumb-item active" aria-current="page">收费执行</li>
                    </ol>
                </div>

            </div>

            <button type="button" class="btn btn-outline-info waves-effect waves-light m-1"
                @click="updatelist">刷新列表</button>


            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-header"><i class="fa fa-table"></i>账单列表</div>
                        <div class="card-body">

                            <div class="table-responsive">
                                <table id="example119" class="table table-bordered">

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

    },
    data() {
        return {
            user: null,
            addbill: false,
            addabill: false,
            billlist: [], // 初始化为空数组
            ready: false, // 初始化为 false,
            update: false,
        }
    },

    watch: {
    },
    methods: {
        async updatelist() {
            await this.destoryDataTable();
            this.initializeDataTable();

        },
        fetchData() {
            try {
                const res = axios.get('http://localhost:8086/house/');
                if (res.status === 200) {
                    console.log('http://localhost:8086/house/');
                    console.log('this.billlist = res.data;');
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
                item.amount,
                item.deadline,
                item.status,
                item.id// 占位符，用于操作列


            ]);
        },
        async initializeDataTable() {
            console.log("initializeDataTable");
            var billlist2d = null;
            try {
                if (!$.fn.DataTable.isDataTable('#example119')) {


                    try {
                        this.user = JSON.parse(localStorage.getItem('user'));
                        const promise = await axios.get('http://localhost:8086/fee/user/' + this.user.id);
                        if (promise.status === 200) {
                            console.log(promise.data);
                            billlist2d = this.convertTo2DArray(promise.data);
                        } else {
                            console.log(promise);
                            return;
                        }
                    } catch (err) {
                        console.error(err);
                        return;
                    }

                    this.$nextTick(() => {
                        console.log(" var table = $('#example119').DataTable({");
                        var table = $('#example119').DataTable({
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
                            data: billlist2d,
                            columns: [
                                { title: '序号' },
                                { title: '收费ID' },
                                { title: '收费用户ID' },
                                { title: '收费类型' },
                                { title: '收费金额(元)' },
                                { title: '收费期限' },
                                { title: '收费状态' },


                                {
                                    title: '操作',
                                    render: function (data) {
                                        return `
                                         <button class="pay-btn btn btn-outline-primary buttons-copy buttons-html5" value=${data}>缴费</button>
 
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
                        $('#example119 tbody').on('click', '.pay-btn', (event) => {
                            var feeid = $(event.target).val();
                            var fee = null;
                            var money = 0;
                            axios.get('http://localhost:8086/fee/' + feeid).then(res => {
                                console.log(res.data);
                                if (res.data != null) {
                                    fee = res.data;
                                    if (fee?.status === '已缴费') {
                                        alert('该账单已缴费');
                                        return;
                                    }
                                    money = fee.amount;
                                    console.log(money);
                                    var user = JSON.parse(localStorage.getItem('user'));
                                    if (user.balance < money) {
                                        alert('余额不足，请充值');
                                        return;
                                    }
                                    user.balance = user.balance - money;

                                    axios.put('http://localhost:8086/user/' + user.id, user).then(res => {
                                        if (res.data) {
                                            fee.status = '已缴费';
                                            axios.put('http://localhost:8086/fee/' + feeid, fee).then(res => {
                                                if (res.data) {
                                                    alert('缴费成功');
                                                    localStorage.setItem('user', JSON.stringify(user));
                                                } else {
                                                    alert('缴费失败');
                                                }
                                            });
                                        } else {
                                            alert('缴费失败');
                                        }
                                    });

                                } else {
                                    alert('该账单不存在');
                                    return;
                                }
                            });


                            // 在这里添加编辑逻辑
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
                if ($.fn.DataTable.isDataTable('#example119')) {
                    console.log("destoryDataTable");
                    $('#example119').DataTable().destroy();
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
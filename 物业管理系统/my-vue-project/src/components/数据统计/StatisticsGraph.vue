<template>
    <div class="content-wrapper">
        <div class="container-fluid">
            <!-- Breadcrumb-->
            <div class="row pt-2 pb-2">
                <div class="col-sm-9">
                    <h4 class="page-title">数据统计</h4>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">报修管理</li>
                        <li class="breadcrumb-item active" aria-current="page">数据统计</li>
                    </ol>
                </div>
            </div>
            <!-- End Breadcrumb-->

            <button type="button" class="btn btn-outline-info waves-effect waves-light m-1"
                @click="updatelist">刷新列表</button>
                <div class="row">
               
                <div id="total-fix-chart" style="width: 800px; height: 400px;"></div>
            </div>
                
           
          <div class="row">
              
                <div id="type-chart" style="width: 800px; height: 400px;"></div>
            </div><!-- End Row-->
          </div><!-- End Row-->
    </div><!-- End Row-->
    <!-- End container-fluid-->
</template>

<script scoped>
import axios from 'axios';
import * as echarts from 'echarts';

export default {
    data() {
        return {
            fixlist: [], // 初始化为空数组
        }
    },
    mounted() {
        this.fetchData();
    },
    methods: {
        async updatelist() {
            await this.fetchData();
            this.renderCharts();
        },
        async fetchData() {
            try {
                const res = await axios.get('http://localhost:8086/fix/');
                if (res.status === 200) {
                    this.fixlist = res.data;
                    this.renderCharts();
                }
            } catch (err) {
                console.error(err);
            }
        },
        renderCharts() {
            // 统计已处理和未处理的报修数量
            const statusData = this.fixlist.reduce((acc, fix) => {
                if (fix.status === '已处理') {
                    acc[0].value++;
                } else {
                    acc[1].value++;
                }
                return acc;
            }, [
                { value: 0, name: '已处理' },
                { value: 0, name: '未处理' }
            ]);

            // 渲染已处理和未处理的报修饼状图
            const totalFixChart = echarts.init(document.getElementById('total-fix-chart'));
            totalFixChart.setOption({
                title: {
                    text: '已处理的报修VS未处理的报修',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left'
                },
                series: [
                    {
                        name: '报修状态',
                        type: 'pie',
                        radius: '50%',
                        data: statusData,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            });

            // 统计报修类型
            const typeData = this.fixlist.reduce((acc, fix) => {
                const type = acc.find(item => item.name === fix.type);
                if (type) {
                    type.value++;
                } else {
                    acc.push({ value: 1, name: fix.type });
                }
                return acc;
            }, []);

            // 渲染报修类型饼状图
            const typeChart = echarts.init(document.getElementById('type-chart'));
            typeChart.setOption({
                title: {
                    text: '报修类型',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left'
                },
                series: [
                    {
                        name: '报修类型',
                        type: 'pie',
                        radius: '50%',
                        data: typeData,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            });
        }
    }
}
</script>

<style scoped>

</style>
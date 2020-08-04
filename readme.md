# 课后练习

备注： demo是每次上课演示的代码，大家提交作业的代码写在homework目录下，
每个人做作业时新建一个分支，请不要向主分支homework目录下提交代码，

## 认识AKKA-课后练习


假设我们有一个物联网设备中心（Device center），用于收集一定范围内的温度，湿度，风俗等信息。
为了保持准备，每个指标都需要多个传感器（Device），多个同类型传感器在一个组（Device group）里面。
传感器组里面每一个传感器每隔一段时间向传感器组发送一次指标。传感器组每隔一段时间统计一次相关指标，
并把结果发给设备中心。假设我们的传感器是温度传感器。


**AC1**，只有一个设备中心（Device center），设备中心可以接受多个设备组回传的消息，
并根据指标有无异常输出相应的信息。如果一段时间内平均温度超过35度，则发出高温预警，
低于-5度，则发出低温预警。

**AC2**， 每个类型的设备有一个设备组，设备组接收设备回传的源数据，并存储起来，
设备组在想设备中心回传信息的时候计算平均温度。设备组每隔10秒回传一次数据，
回传信息包含各个地点的平均温度，每次回传之后清除历史设备数据

**AC3**，同种类型的设备在一个设备组下面，设备每秒回传一次数据，回传信息包括地点和温度。
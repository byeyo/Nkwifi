# Nkwifi
#### 5.2.12.529.9版本以后的创翼/Netkeeper时开启wifi的GUI脚本

---
<ol>
<li>pssuspend64:https://docs.microsoft.com/zh-cn/sysinternals/downloads/pssuspend
<li>将编译好的jar包和pssuspend64.exe放在一起
<li>或者直接下载 [编译好的版本+pssuspend64.exe](http://www.byeyo.com:8080/DownloadPrj/main)
<li>第一次使用需要双击pssuspend64.exe安装
</ol>


原理
---
pssuspend64.exe用于挂起进程，使目标进程假死

```
可以执行

pssuspend64.exe processName   

来手动挂起进程

```

说明：挂起Netkeeper进程，使之无法检测wifi和断网

如无法使用pssuspend64.exe悬挂进程，只有另寻它法了

若未开启wifi就已经提示检测到wifi了,说明有残留的wifi环境,请根据实际情况重写Tool.cancelWifiState()

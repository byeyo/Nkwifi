# Nkwifi
#### 5.2.12.529.9版本以后的创翼/Netkeeper时开启wifi的GUI脚本

---
<ol>
<li>下载pssuspend64:https://docs.microsoft.com/zh-cn/sysinternals/downloads/pssuspend
<li>将编译好的jar包和pssuspend64.exe放在一起
<li><h>或者直接下载</h><a href="http://www.byeyo.com:8080/">编译好的版本+pssuspend64.exe</a> 
<li>第一次使用需要双击pssuspend64.exe安装
<li>安装完成后即可正常使用jar脚本
</ol>


原理
---
pssuspend64.exe用于挂起进程，使目标进程假死

```
执行

pssuspend64.exe processName   

手动挂起进程

执行

pssuspend64.exe -r processName   

手动恢复进程

```

说明：挂起Netkeeper进程，使之无法检测wifi和断网

如无法使用pssuspend64.exe悬挂进程，可能是权限不够，可以使用nircmd.exe来执行cmd命令

若未开启wifi就已经提示检测到wifi了,说明有残留的wifi环境,请根据实际情况重写Tool.cancelWifiState()

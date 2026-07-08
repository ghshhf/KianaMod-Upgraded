@echo off
chcp 65001 >nul
setlocal

REM ===== 路径配置 =====
set WORKSHOP=F:\steam\steamapps\workshop\content\646570\3559292072
set DIST=E:\项目改变\KianaMod-m3\dist
set ORIG=kiana-mod-original-v1.0.0.jar
set UP=kiana-mod-upgraded-v1.1.0.jar
set TARGET=kiana-mod.jar

REM ===== 校验 =====
if not exist "%WORKSHOP%\%TARGET%" (
  echo [错误] 未找到工坊 mod 文件：
  echo   %WORKSHOP%\%TARGET%
  echo 请确认游戏与 mod 安装路径是否正确。
  pause
  exit /b 1
)
if not exist "%DIST%\%ORIG%" (
  echo [错误] 找不到原版备份：%DIST%\%ORIG%
  pause
  exit /b 1
)
if not exist "%DIST%\%UP%" (
  echo [错误] 找不到升级版：%DIST%\%UP%
  pause
  exit /b 1
)

echo ============================================
echo   KianaMod 版本切换器
echo   原版 : kiana-mod-original-v1.0.0.jar
echo   升级 : kiana-mod-upgraded-v1.1.0.jar (薪炎之律者 12 卡)
echo ============================================
echo.
echo 切换前，当前工坊目录里激活的那一份会被自动备份到
echo   %DIST%\kiana-mod-current-backup.jar
echo.

set /p CHOICE="输入 O 启用原版 / E 启用升级版（默认 E）: "

if /i "%CHOICE%"=="O" goto ORIG
goto UPGRADE

:ORIG
copy /Y "%WORKSHOP%\%TARGET%" "%DIST%\kiana-mod-current-backup.jar" >nul
copy /Y "%DIST%\%ORIG%" "%WORKSHOP%\%TARGET%" >nul
echo [完成] 已切换到 原版 v1.0.0。请用 ModTheSpire 重启游戏。
goto END

:UPGRADE
copy /Y "%WORKSHOP%\%TARGET%" "%DIST%\kiana-mod-current-backup.jar" >nul
copy /Y "%DIST%\%UP%" "%WORKSHOP%\%TARGET%" >nul
echo [完成] 已切换到 升级版 v1.1.0（薪炎之律者）。请用 ModTheSpire 重启游戏。
goto END

:END
echo.
pause

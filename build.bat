@echo off
chcp 65001 >nul
setlocal EnableDelayedExpansion

set SRC=E:\项目改变\KianaMod-m3\src
set MAIN=E:\项目改变\KianaMod-m3\src\main\java
set OUT=E:\项目改变\KianaMod-m3\classes
set DIST=E:\项目改变\KianaMod-m3\dist
set TOOLS=E:\项目改变\KianaMod-m3\tools
set JAVAC=G:\环境\bin\javac.exe

echo ============================================
echo  KianaMod Build Script (v1.1.0)
echo  编译 + 打包 + 验证
echo ============================================
echo.

:: === Step 1: Classpath ===
set CP=F:\steam\steamapps\common\SlayTheSpire\desktop-1.0.jar
for %%j in (F:\steam\steamapps\workshop\content\646570\*\*.jar) do set CP=!CP!;%%j
echo [1/4] Classpath: %CP%
echo.

:: === Step 2: Compile ===
echo [2/4] Compiling 41 source files (--release 8)...
if exist "%OUT%" rmdir /s /q "%OUT%"
mkdir "%OUT%"
"%JAVAC%" -encoding UTF-8 --release 8 -proc:none -cp "%CP%" -d "%OUT%" ^
  "%SRC%\kianamod\modcore\ExampleMod.java" ^
  "%MAIN%\kianamod\card\FlamePursuit.java" ^
  "%MAIN%\kianamod\card\CrimsonCharge.java" ^
  "%MAIN%\kianamod\card\PassFlame.java" ^
  "%MAIN%\kianamod\card\SkyfireDraw.java" ^
  "%MAIN%\kianamod\card\EmberRekindle.java" ^
  "%MAIN%\kianamod\card\BurnOut.java" ^
  "%MAIN%\kianamod\card\ValkyrieOath.java" ^
  "%MAIN%\kianamod\card\FlameStorm.java" ^
  "%MAIN%\kianamod\card\Cinder.java" ^
  "%MAIN%\kianamod\card\PrairieFire.java" ^
  "%MAIN%\kianamod\card\FlameDragonBreak.java" ^
  "%MAIN%\kianamod\card\GuardianFlame.java" ^
  "%MAIN%\kianamod\card\VoidSlash.java" ^
  "%MAIN%\kianamod\card\SpaceFold.java" ^
  "%MAIN%\kianamod\card\QuantumTunnel.java" ^
  "%MAIN%\kianamod\card\GravitySingularity.java" ^
  "%MAIN%\kianamod\card\ImaginaryFill.java" ^
  "%MAIN%\kianamod\card\SubspaceWalker.java" ^
  "%MAIN%\kianamod\card\DimensionBanish.java" ^
  "%MAIN%\kianamod\card\StellarOrbit.java" ^
  "%MAIN%\kianamod\card\VoidDrain.java" ^
  "%MAIN%\kianamod\card\RiftWorld.java" ^
  "%MAIN%\kianamod\card\TimeShift.java" ^
  "%MAIN%\kianamod\card\SingularityBurst.java" ^
  "%MAIN%\kianamod\card\FinalJudgment.java" ^
  "%MAIN%\kianamod\card\BlazeStance.java" ^
  "%MAIN%\kianamod\card\MoonStance.java" ^
  "%MAIN%\kianamod\card\TimeTide.java" ^
  "%MAIN%\kianamod\card\AbsoluteTimeBreak.java" ^
  "%MAIN%\kianamod\card\FinalStance.java" ^
  "%MAIN%\kianamod\card\FinalResolution.java" ^
  "%MAIN%\kianamod\card\VoidAscension.java" ^
  "%MAIN%\kianamod\card\MemoryFeather.java" ^
  "%MAIN%\kianamod\card\MoonlightBlade.java" ^
  "%MAIN%\kianamod\powers\BurnOutPower.java" ^
  "%MAIN%\kianamod\util\FlameCardUtils.java" ^
  "%MAIN%\kianamod\relics\HeavenFireMark.java" ^
  "%MAIN%\kianamod\relics\VoidRealm.java" ^
  "%MAIN%\kianamod\relics\FinalityOath.java" ^
  "%MAIN%\kianamod\relics\Moonlight.java" ^
  "%MAIN%\kianamod\relics\TruthKey.java" ^
  "%MAIN%\kianamod\relics\BloodRose.java" ^
  "%MAIN%\kianamod\relics\BlackWhiteFlower.java" ^
  "%MAIN%\kianamod\relics\EdenStar.java" ^
  "%MAIN%\kianamod\events\SaintFreyaAcademyEvent.java" ^
  "%MAIN%\kianamod\events\ChangKongNightEvent.java" ^
  "%MAIN%\kianamod\events\TianMingHeadquartersEvent.java" ^
  "%MAIN%\kianamod\events\NiSheProposalEvent.java" ^
  "%MAIN%\kianamod\events\KianaDreamEvent.java" ^
  "%MAIN%\kianamod\events\AlliancePower.java"
if %errorlevel% neq 0 (echo [ERROR] Compilation failed. & pause & exit /b 1)
echo   0 errors, 23 files compiled.
echo.

:: === Step 3: Package jar ===
echo [3/4] Packaging jar...
python -u "%~dp0build_pack.py"
if %errorlevel% neq 0 (echo [ERROR] Packaging failed. & pause & exit /b 1)
echo.
echo === Output: %DIST%\kiana-mod-upgraded-v1.1.0.jar ===
echo.

:: === Step 4: Java 8 load test ===
echo [4/4] Java 8 runtime verification...
set JRE8=F:\steam\steamapps\common\SlayTheSpire\jre\bin\java.exe
set JARS=%DIST%\kiana-mod-upgraded-v1.1.0.jar
set JARS=%JARS%;F:\steam\steamapps\common\SlayTheSpire\desktop-1.0.jar
for %%j in (F:\steam\steamapps\workshop\content\646570\*\*.jar) do set JARS=!JARS!;%%j
"%JRE8%" -cp "%TOOLS%;%JARS%" LoadTest %JARS% 2>&1
if %errorlevel% neq 0 (echo [ERROR] Load test failed. & pause & exit /b 1)

echo.
echo ============================================
echo  BUILD COMPLETE - All verified
echo ============================================
pause

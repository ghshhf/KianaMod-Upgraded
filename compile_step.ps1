$SRC = "E:\项目改变\KianaMod-m3\src"
$MAIN = "$SRC\main\java"
$OUT = "E:\项目改变\KianaMod-m3\classes"
$JAVAC = "G:\环境\bin\javac.exe"

# Build classpath
$CP = @("F:\steam\steamapps\common\SlayTheSpire\desktop-1.0.jar")
Get-ChildItem "F:\steam\steamapps\workshop\content\646570\*\*.jar" | ForEach-Object { $CP += $_.FullName }
$separator = [string]($CP -join ";")

Write-Host "Classpath jars: $($CP.Count)"

# Clean output
if (Test-Path $OUT) { Remove-Item -Recurse -Force $OUT }
New-Item -ItemType Directory -Force -Path $OUT | Out-Null

# Build source files list
$srcFiles = @(
    "$SRC\kianamod\modcore\ExampleMod.java",
    "$MAIN\kianamod\card\FlamePursuit.java",
    "$MAIN\kianamod\card\CrimsonCharge.java",
    "$MAIN\kianamod\card\PassFlame.java",
    "$MAIN\kianamod\card\SkyfireDraw.java",
    "$MAIN\kianamod\card\EmberRekindle.java",
    "$MAIN\kianamod\card\BurnOut.java",
    "$MAIN\kianamod\card\ValkyrieOath.java",
    "$MAIN\kianamod\card\FlameStorm.java",
    "$MAIN\kianamod\card\Cinder.java",
    "$MAIN\kianamod\card\PrairieFire.java",
    "$MAIN\kianamod\card\FlameDragonBreak.java",
    "$MAIN\kianamod\card\GuardianFlame.java",
    "$MAIN\kianamod\card\VoidSlash.java",
    "$MAIN\kianamod\card\SpaceFold.java",
    "$MAIN\kianamod\card\QuantumTunnel.java",
    "$MAIN\kianamod\card\GravitySingularity.java",
    "$MAIN\kianamod\card\ImaginaryFill.java",
    "$MAIN\kianamod\card\SubspaceWalker.java",
    "$MAIN\kianamod\card\DimensionBanish.java",
    "$MAIN\kianamod\card\StellarOrbit.java",
    "$MAIN\kianamod\card\VoidDrain.java",
    "$MAIN\kianamod\card\RiftWorld.java",
    "$MAIN\kianamod\card\TimeShift.java",
    "$MAIN\kianamod\card\SingularityBurst.java",
    # M5: 终焉之律者扩展 10 卡
    "$MAIN\kianamod\card\FinalJudgment.java",
    "$MAIN\kianamod\card\BlazeStance.java",
    "$MAIN\kianamod\card\MoonStance.java",
    "$MAIN\kianamod\card\TimeTide.java",
    "$MAIN\kianamod\card\AbsoluteTimeBreak.java",
    "$MAIN\kianamod\card\FinalStance.java",
    "$MAIN\kianamod\card\FinalResolution.java",
    "$MAIN\kianamod\card\VoidAscension.java",
    "$MAIN\kianamod\card\MemoryFeather.java",
    "$MAIN\kianamod\card\MoonlightBlade.java",
    "$MAIN\kianamod\powers\BurnOutPower.java",
    "$MAIN\kianamod\util\FlameCardUtils.java",
    "$MAIN\kianamod\relics\HeavenFireMark.java",
    "$MAIN\kianamod\relics\VoidRealm.java",
    "$MAIN\kianamod\relics\FinalityOath.java",
    "$MAIN\kianamod\relics\Moonlight.java",
    "$MAIN\kianamod\relics\TruthKey.java",
    "$MAIN\kianamod\relics\BloodRose.java",
    "$MAIN\kianamod\relics\BlackWhiteFlower.java",
    "$MAIN\kianamod\relics\EdenStar.java",
    # M7-M8: Events
    "$MAIN\kianamod\events\SaintFreyaAcademyEvent.java",
    "$MAIN\kianamod\events\ChangKongNightEvent.java",
    "$MAIN\kianamod\events\TianMingHeadquartersEvent.java",
    "$MAIN\kianamod\events\NiSheProposalEvent.java",
    "$MAIN\kianamod\events\KianaDreamEvent.java",
    "$MAIN\kianamod\events\AlliancePower.java"
)

Write-Host "Compiling..."
$argsList = @(
    "-encoding", "UTF-8",
    "--release", "8",
    "-proc:none",
    "-cp", $separator,
    "-d", $OUT
) + $srcFiles

$p = Start-Process -Wait -NoNewWindow -PassThru -FilePath $JAVAC -ArgumentList $argsList
Write-Host "Exit code: $($p.ExitCode)"

if ($p.ExitCode -eq 0) {
    $count = (Get-ChildItem -Recurse -Filter "*.class" $OUT).Count
    Write-Host "OK - $count class files generated"
} else {
    Write-Host "COMPILATION FAILED"
}

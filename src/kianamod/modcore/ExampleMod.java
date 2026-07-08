/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  basemod.BaseMod
 *  basemod.helpers.RelicType
 *  basemod.interfaces.EditCardsSubscriber
 *  basemod.interfaces.EditCharactersSubscriber
 *  basemod.interfaces.EditKeywordsSubscriber
 *  basemod.interfaces.EditRelicsSubscriber
 *  basemod.interfaces.EditStringsSubscriber
 *  basemod.interfaces.ISubscriber
 *  basemod.interfaces.PostInitializeSubscriber
 *  com.badlogic.gdx.Gdx
 *  com.badlogic.gdx.graphics.Color
 *  com.evacipated.cardcrawl.modthespire.lib.SpireInitializer
 *  com.google.gson.Gson
 *  com.megacrit.cardcrawl.cards.AbstractCard
 *  com.megacrit.cardcrawl.cards.AbstractCard$CardColor
 *  com.megacrit.cardcrawl.characters.AbstractPlayer
 *  com.megacrit.cardcrawl.characters.AbstractPlayer$PlayerClass
 *  com.megacrit.cardcrawl.core.CardCrawlGame
 *  com.megacrit.cardcrawl.core.Settings
 *  com.megacrit.cardcrawl.core.Settings$GameLanguage
 *  com.megacrit.cardcrawl.localization.CardStrings
 *  com.megacrit.cardcrawl.localization.CharacterStrings
 *  com.megacrit.cardcrawl.localization.Keyword
 *  com.megacrit.cardcrawl.localization.PotionStrings
 *  com.megacrit.cardcrawl.localization.PowerStrings
 *  com.megacrit.cardcrawl.localization.RelicStrings
 *  com.megacrit.cardcrawl.localization.StanceStrings
 *  com.megacrit.cardcrawl.relics.AbstractRelic
 *  kianamod.Potion.AbyssalLawbringer2Potion
 *  kianamod.Potion.AbyssalLawbringerPotion
 *  kianamod.Potion.HonkaiEnergyPotion
 *  kianamod.Potion.ProtectionPotion
 *  kianamod.Potion.StancePotion2
 *  kianamod.Potion.StancePotion3
 *  kianamod.card.AbyssalDescent
 *  kianamod.card.AbyssalLawbringer
 *  kianamod.card.AbyssalSpear
 *  kianamod.card.Accelerate
 *  kianamod.card.BlazeChariotsBeaconSmoke
 *  kianamod.card.BlazeoftheKingSword
 *  kianamod.card.BreakThroughtheEnd
 *  kianamod.card.ChooseEnd
 *  kianamod.card.ChooseFlame
 *  kianamod.card.ChooseSpace
 *  kianamod.card.ChronoEnergyFlow
 *  kianamod.card.CometImpact
 *  kianamod.card.Defend_AZURE
 *  kianamod.card.DivineBlessingandPrayer
 *  kianamod.card.DivineLight
 *  kianamod.card.DodgeCooldown
 *  kianamod.card.EndlessRealm
 *  kianamod.card.EnergyCounterflow
 *  kianamod.card.EnergyDispersion
 *  kianamod.card.EnergyFlowDeviation
 *  kianamod.card.EternalBlazingFlame
 *  kianamod.card.Evasion
 *  kianamod.card.FlameWheel
 *  kianamod.card.FlameofResistance
 *  kianamod.card.FlawlessTrueSelf
 *  kianamod.card.GravityDaze
 *  kianamod.card.GuardWithThisOde
 *  kianamod.card.GungnirExecution
 *  kianamod.card.HerrscherofDestruction
 *  kianamod.card.HibiscusCoreGuardian
 *  kianamod.card.IamtheHonkai
 *  kianamod.card.Iamthebeacon
 *  kianamod.card.ImaginaryThorns
 *  kianamod.card.InterdimensionalLeap
 *  kianamod.card.ItisKianaTime
 *  kianamod.card.Leader
 *  kianamod.card.MeteorImpact
 *  kianamod.card.MeteorShower
 *  kianamod.card.MeteorStrike
 *  kianamod.card.MeteorStrikeSkill
 *  kianamod.card.MeteorTarget
 *  kianamod.card.NebulaImpact
 *  kianamod.card.NightshadeRanger
 *  kianamod.card.NovaDash
 *  kianamod.card.NovaFire
 *  kianamod.card.PhaseShift
 *  kianamod.card.PhoenixsFlame
 *  kianamod.card.PlagueGem
 *  kianamod.card.RiftSiphon
 *  kianamod.card.SpacetimeQuake
 *  kianamod.card.Strike
 *  kianamod.card.SuborbitalRapidFire
 *  kianamod.card.SummoningtheEngravedFeathersRebirth
 *  kianamod.card.SwordBurn
 *  kianamod.card.TemporalRift
 *  kianamod.card.TheBeginningandEndofFlawlessProtection
 *  kianamod.card.TheJourneyWithYou
 *  kianamod.card.TheNamelessTravelerJourney
 *  kianamod.card.TheWorldNamedToday
 *  kianamod.card.TimeFlash
 *  kianamod.card.TimeGapProtection
 *  kianamod.card.TimeRift
 *  kianamod.card.TracetheMoonlighttoForgeaSword
 *  kianamod.card.TranquilityGem
 *  kianamod.card.TriumphoftheFlameKingSword
 *  kianamod.card.TrueUnderstandingoftheWanderersEnd
 *  kianamod.card.TurntheLoopofTruth
 *  kianamod.card.TwilightsBenediction
 *  kianamod.card.ValkyrieBurst
 *  kianamod.card.ValkyrieDurability
 *  kianamod.card.ValkyrieGuardian
 *  kianamod.card.ValkyrieImpact
 *  kianamod.card.VoidAuthority
 *  kianamod.card.VoidImpact
 *  kianamod.card.Withthisblazingfirenothingshallstandunbroken2
 *  kianamod.card.WiththisflameIshallburnbrightly
 *  kianamod.card.WiththisswordsradianceIshallgrasp
 *  kianamod.card.ZhiLin
 *  kianamod.characters.MyCharacter
 *  kianamod.characters.MyCharacter$PlayerColorEnum
 *  kianamod.relics.Acandleinthefiercewind
 *  kianamod.relics.Celine
 *  kianamod.relics.DivineFallSwordSilter
 *  kianamod.relics.HHkey
 *  kianamod.relics.Startingwithme
 *  kianamod.relics.Surname
 *  kianamod.relics.Surname2
 *  kianamod.relics.TheHuberian
 *  kianamod.relics.Thekeytoemptiness
 */
package kianamod.modcore;

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.ISubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.Keyword;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import java.nio.charset.StandardCharsets;
import kianamod.Potion.AbyssalLawbringer2Potion;
import kianamod.Potion.AbyssalLawbringerPotion;
import kianamod.Potion.HonkaiEnergyPotion;
import kianamod.Potion.ProtectionPotion;
import kianamod.Potion.StancePotion2;
import kianamod.Potion.StancePotion3;
import kianamod.card.AbyssalDescent;
import kianamod.card.AbyssalLawbringer;
import kianamod.card.AbyssalSpear;
import kianamod.card.Accelerate;
import kianamod.card.BlazeChariotsBeaconSmoke;
import kianamod.card.BlazeoftheKingSword;
import kianamod.card.BreakThroughtheEnd;
import kianamod.card.ChooseEnd;
import kianamod.card.ChooseFlame;
import kianamod.card.ChooseSpace;
import kianamod.card.ChronoEnergyFlow;
import kianamod.card.CometImpact;
import kianamod.card.Defend_AZURE;
import kianamod.card.DivineBlessingandPrayer;
import kianamod.card.DivineLight;
import kianamod.card.DodgeCooldown;
import kianamod.card.EndlessRealm;
import kianamod.card.EnergyCounterflow;
import kianamod.card.EnergyDispersion;
import kianamod.card.EnergyFlowDeviation;
import kianamod.card.EternalBlazingFlame;
import kianamod.card.Evasion;
import kianamod.card.FlameWheel;
import kianamod.card.FlameofResistance;
import kianamod.card.FlawlessTrueSelf;
import kianamod.card.GravityDaze;
import kianamod.card.GuardWithThisOde;
import kianamod.card.GungnirExecution;
import kianamod.card.HerrscherofDestruction;
import kianamod.card.HibiscusCoreGuardian;
import kianamod.card.IamtheHonkai;
import kianamod.card.Iamthebeacon;
import kianamod.card.ImaginaryThorns;
import kianamod.card.InterdimensionalLeap;
import kianamod.card.ItisKianaTime;
import kianamod.card.Leader;
import kianamod.card.MeteorImpact;
import kianamod.card.MeteorShower;
import kianamod.card.MeteorStrike;
import kianamod.card.MeteorStrikeSkill;
import kianamod.card.MeteorTarget;
import kianamod.card.NebulaImpact;
import kianamod.card.NightshadeRanger;
import kianamod.card.NovaDash;
import kianamod.card.NovaFire;
import kianamod.card.PhaseShift;
import kianamod.card.PhoenixsFlame;
import kianamod.card.PlagueGem;
import kianamod.card.RiftSiphon;
import kianamod.card.SpacetimeQuake;
import kianamod.card.Strike;
import kianamod.card.SuborbitalRapidFire;
import kianamod.card.SummoningtheEngravedFeathersRebirth;
import kianamod.card.SwordBurn;
import kianamod.card.TemporalRift;
import kianamod.card.TheBeginningandEndofFlawlessProtection;
import kianamod.card.TheJourneyWithYou;
import kianamod.card.TheNamelessTravelerJourney;
import kianamod.card.TheWorldNamedToday;
import kianamod.card.TimeFlash;
import kianamod.card.TimeGapProtection;
import kianamod.card.TimeRift;
import kianamod.card.TracetheMoonlighttoForgeaSword;
import kianamod.card.TranquilityGem;
import kianamod.card.TriumphoftheFlameKingSword;
import kianamod.card.TrueUnderstandingoftheWanderersEnd;
import kianamod.card.TurntheLoopofTruth;
import kianamod.card.TwilightsBenediction;
import kianamod.card.ValkyrieBurst;
import kianamod.card.ValkyrieDurability;
import kianamod.card.ValkyrieGuardian;
import kianamod.card.ValkyrieImpact;
import kianamod.card.VoidAuthority;
import kianamod.card.VoidImpact;
import kianamod.card.Withthisblazingfirenothingshallstandunbroken2;
import kianamod.card.WiththisflameIshallburnbrightly;
import kianamod.card.WiththisswordsradianceIshallgrasp;
import kianamod.card.ZhiLin;
import kianamod.card.FlamePursuit;
import kianamod.card.CrimsonCharge;
import kianamod.card.PassFlame;
import kianamod.card.SkyfireDraw;
import kianamod.card.EmberRekindle;
import kianamod.card.BurnOut;
import kianamod.card.ValkyrieOath;
import kianamod.card.FlameStorm;
import kianamod.card.Cinder;
import kianamod.card.PrairieFire;
import kianamod.card.FlameDragonBreak;
import kianamod.card.GuardianFlame;
import kianamod.card.VoidSlash;
import kianamod.card.SpaceFold;
import kianamod.card.QuantumTunnel;
import kianamod.card.GravitySingularity;
import kianamod.card.ImaginaryFill;
import kianamod.card.SubspaceWalker;
import kianamod.card.DimensionBanish;
import kianamod.card.StellarOrbit;
import kianamod.card.VoidDrain;
import kianamod.card.RiftWorld;
import kianamod.card.TimeShift;
import kianamod.card.SingularityBurst;
// M5: 终焉之律者扩展 10 卡
import kianamod.card.FinalJudgment;
import kianamod.card.BlazeStance;
import kianamod.card.MoonStance;
import kianamod.card.TimeTide;
import kianamod.card.AbsoluteTimeBreak;
import kianamod.card.FinalStance;
import kianamod.card.FinalResolution;
import kianamod.card.VoidAscension;
import kianamod.card.MemoryFeather;
import kianamod.card.MoonlightBlade;
import kianamod.characters.MyCharacter;
import kianamod.relics.Acandleinthefiercewind;
import kianamod.relics.Celine;
import kianamod.relics.DivineFallSwordSilter;
import kianamod.relics.HHkey;
import kianamod.relics.Startingwithme;
import kianamod.relics.Surname;
import kianamod.relics.Surname2;
import kianamod.relics.TheHuberian;
import kianamod.relics.Thekeytoemptiness;
import kianamod.relics.HeavenFireMark;
import kianamod.relics.VoidRealm;
import kianamod.relics.FinalityOath;
import kianamod.relics.Moonlight;
import kianamod.relics.TruthKey;
import kianamod.relics.BloodRose;
import kianamod.relics.BlackWhiteFlower;
import kianamod.relics.EdenStar;
import kianamod.events.SaintFreyaAcademyEvent;
import kianamod.events.ChangKongNightEvent;
import kianamod.events.TianMingHeadquartersEvent;
import kianamod.events.NiSheProposalEvent;
import kianamod.events.KianaDreamEvent;

@SpireInitializer
public class ExampleMod
implements EditCardsSubscriber,
EditStringsSubscriber,
EditCharactersSubscriber,
EditRelicsSubscriber,
PostInitializeSubscriber,
EditKeywordsSubscriber {
    private static final String MY_CHARACTER_BUTTON = "ExampleModResources/img/char/Character_Button.png";
    private static final String MY_CHARACTER_PORTRAIT = "ExampleModResources/img/char/Character_Portrait.png";
    private static final String BG_ATTACK_512 = "ExampleModResources/img/512/bg_attack_512.png";
    private static final String BG_POWER_512 = "ExampleModResources/img/512/bg_power_512.png";
    private static final String BG_SKILL_512 = "ExampleModResources/img/512/bg_skill_512.png";
    private static final String SMALL_ORB = "ExampleModResources/img/char/small_orb.png";
    private static final String BG_ATTACK_1024 = "ExampleModResources/img/1024/bg_attack.png";
    private static final String BG_POWER_1024 = "ExampleModResources/img/1024/bg_power.png";
    private static final String BG_SKILL_1024 = "ExampleModResources/img/1024/bg_skill.png";
    private static final String BIG_ORB = "ExampleModResources/img/char/card_orb.png";
    private static final String ENEYGY_ORB = "ExampleModResources/img/char/cost_orb.png";
    public static final Color MY_COLOR = new Color(0.13333334f, 0.68235296f, 0.9019608f, 1.0f);

    public ExampleMod() {
        BaseMod.subscribe((ISubscriber)this);
        BaseMod.addColor((AbstractCard.CardColor)MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, (Color)MY_COLOR, (Color)MY_COLOR, (Color)MY_COLOR, (Color)MY_COLOR, (Color)MY_COLOR, (Color)MY_COLOR, (Color)MY_COLOR, (String)BG_ATTACK_512, (String)BG_SKILL_512, (String)BG_POWER_512, (String)ENEYGY_ORB, (String)BG_ATTACK_1024, (String)BG_SKILL_1024, (String)BG_POWER_1024, (String)BIG_ORB, (String)SMALL_ORB);
    }

    public static void initialize() {
        new ExampleMod();
    }

    public void receiveEditCards() {
        BaseMod.addCard((AbstractCard)new Strike());
        BaseMod.addCard((AbstractCard)new SwordBurn());
        BaseMod.addCard((AbstractCard)new Evasion());
        BaseMod.addCard((AbstractCard)new TriumphoftheFlameKingSword());
        BaseMod.addCard((AbstractCard)new EnergyCounterflow());
        BaseMod.addCard((AbstractCard)new Defend_AZURE());
        BaseMod.addCard((AbstractCard)new EnergyFlowDeviation());
        BaseMod.addCard((AbstractCard)new MeteorTarget());
        BaseMod.addCard((AbstractCard)new CometImpact());
        BaseMod.addCard((AbstractCard)new TheJourneyWithYou());
        BaseMod.addCard((AbstractCard)new TheNamelessTravelerJourney());
        BaseMod.addCard((AbstractCard)new VoidAuthority());
        BaseMod.addCard((AbstractCard)new GravityDaze());
        BaseMod.addCard((AbstractCard)new GuardWithThisOde());
        BaseMod.addCard((AbstractCard)new ChronoEnergyFlow());
        BaseMod.addCard((AbstractCard)new RiftSiphon());
        BaseMod.addCard((AbstractCard)new Leader());
        BaseMod.addCard((AbstractCard)new TranquilityGem());
        BaseMod.addCard((AbstractCard)new MeteorImpact());
        BaseMod.addCard((AbstractCard)new BlazeChariotsBeaconSmoke());
        BaseMod.addCard((AbstractCard)new SuborbitalRapidFire());
        BaseMod.addCard((AbstractCard)new NebulaImpact());
        BaseMod.addCard((AbstractCard)new MeteorShower());
        BaseMod.addCard((AbstractCard)new ValkyrieDurability());
        BaseMod.addCard((AbstractCard)new NovaDash());
        BaseMod.addCard((AbstractCard)new MeteorStrike());
        BaseMod.addCard((AbstractCard)new InterdimensionalLeap());
        BaseMod.addCard((AbstractCard)new BlazeoftheKingSword());
        BaseMod.addCard((AbstractCard)new TimeRift());
        BaseMod.addCard((AbstractCard)new ValkyrieImpact());
        BaseMod.addCard((AbstractCard)new HibiscusCoreGuardian());
        BaseMod.addCard((AbstractCard)new TimeGapProtection());
        BaseMod.addCard((AbstractCard)new TurntheLoopofTruth());
        BaseMod.addCard((AbstractCard)new EndlessRealm());
        BaseMod.addCard((AbstractCard)new ValkyrieBurst());
        BaseMod.addCard((AbstractCard)new EnergyDispersion());
        BaseMod.addCard((AbstractCard)new NightshadeRanger());
        BaseMod.addCard((AbstractCard)new MeteorStrikeSkill());
        BaseMod.addCard((AbstractCard)new TemporalRift());
        BaseMod.addCard((AbstractCard)new VoidImpact());
        BaseMod.addCard((AbstractCard)new ImaginaryThorns());
        BaseMod.addCard((AbstractCard)new GungnirExecution());
        BaseMod.addCard((AbstractCard)new DivineLight());
        BaseMod.addCard((AbstractCard)new PhaseShift());
        BaseMod.addCard((AbstractCard)new ValkyrieGuardian());
        BaseMod.addCard((AbstractCard)new NovaFire());
        BaseMod.addCard((AbstractCard)new AbyssalDescent());
        BaseMod.addCard((AbstractCard)new AbyssalSpear());
        BaseMod.addCard((AbstractCard)new AbyssalLawbringer());
        BaseMod.addCard((AbstractCard)new PhoenixsFlame());
        BaseMod.addCard((AbstractCard)new TwilightsBenediction());
        BaseMod.addCard((AbstractCard)new SpacetimeQuake());
        BaseMod.addCard((AbstractCard)new SummoningtheEngravedFeathersRebirth());
        BaseMod.addCard((AbstractCard)new WiththisswordsradianceIshallgrasp());
        BaseMod.addCard((AbstractCard)new PlagueGem());
        BaseMod.addCard((AbstractCard)new EternalBlazingFlame());
        BaseMod.addCard((AbstractCard)new TheBeginningandEndofFlawlessProtection());
        BaseMod.addCard((AbstractCard)new DodgeCooldown());
        BaseMod.addCard((AbstractCard)new TheWorldNamedToday());
        BaseMod.addCard((AbstractCard)new FlameWheel());
        BaseMod.addCard((AbstractCard)new Withthisblazingfirenothingshallstandunbroken2());
        BaseMod.addCard((AbstractCard)new TrueUnderstandingoftheWanderersEnd());
        BaseMod.addCard((AbstractCard)new TimeFlash());
        BaseMod.addCard((AbstractCard)new IamtheHonkai());
        BaseMod.addCard((AbstractCard)new WiththisflameIshallburnbrightly());
        BaseMod.addCard((AbstractCard)new HerrscherofDestruction());
        BaseMod.addCard((AbstractCard)new DivineBlessingandPrayer());
        BaseMod.addCard((AbstractCard)new FlameofResistance());
        BaseMod.addCard((AbstractCard)new BreakThroughtheEnd());
        BaseMod.addCard((AbstractCard)new Iamthebeacon());
        BaseMod.addCard((AbstractCard)new TracetheMoonlighttoForgeaSword());
        BaseMod.addCard((AbstractCard)new Accelerate());
        BaseMod.addCard((AbstractCard)new ItisKianaTime());
        BaseMod.addCard((AbstractCard)new ZhiLin());
        BaseMod.addCard((AbstractCard)new FlawlessTrueSelf());
        BaseMod.addCard((AbstractCard)new FlamePursuit());
        BaseMod.addCard((AbstractCard)new CrimsonCharge());
        BaseMod.addCard((AbstractCard)new PassFlame());
        BaseMod.addCard((AbstractCard)new SkyfireDraw());
        BaseMod.addCard((AbstractCard)new EmberRekindle());
        BaseMod.addCard((AbstractCard)new BurnOut());
        BaseMod.addCard((AbstractCard)new ValkyrieOath());
        BaseMod.addCard((AbstractCard)new FlameStorm());
        BaseMod.addCard((AbstractCard)new Cinder());
        BaseMod.addCard((AbstractCard)new PrairieFire());
        BaseMod.addCard((AbstractCard)new FlameDragonBreak());
        BaseMod.addCard((AbstractCard)new GuardianFlame());
        // M4: 空之律者扩展 12 卡
        BaseMod.addCard((AbstractCard)new VoidSlash());
        BaseMod.addCard((AbstractCard)new SpaceFold());
        BaseMod.addCard((AbstractCard)new QuantumTunnel());
        BaseMod.addCard((AbstractCard)new GravitySingularity());
        BaseMod.addCard((AbstractCard)new ImaginaryFill());
        BaseMod.addCard((AbstractCard)new SubspaceWalker());
        BaseMod.addCard((AbstractCard)new DimensionBanish());
        BaseMod.addCard((AbstractCard)new StellarOrbit());
        BaseMod.addCard((AbstractCard)new VoidDrain());
        BaseMod.addCard((AbstractCard)new RiftWorld());
        BaseMod.addCard((AbstractCard)new TimeShift());
        BaseMod.addCard((AbstractCard)new SingularityBurst());
        // M5: 终焉之律者扩展 10 卡
        BaseMod.addCard((AbstractCard)new FinalJudgment());
        BaseMod.addCard((AbstractCard)new BlazeStance());
        BaseMod.addCard((AbstractCard)new MoonStance());
        BaseMod.addCard((AbstractCard)new TimeTide());
        BaseMod.addCard((AbstractCard)new AbsoluteTimeBreak());
        BaseMod.addCard((AbstractCard)new FinalStance());
        BaseMod.addCard((AbstractCard)new FinalResolution());
        BaseMod.addCard((AbstractCard)new VoidAscension());
        BaseMod.addCard((AbstractCard)new MemoryFeather());
        BaseMod.addCard((AbstractCard)new MoonlightBlade());
    }

    public void receiveEditStrings() {
        String lang = Settings.language == Settings.GameLanguage.ZHS ? "ZHS" : "ENG";
        BaseMod.loadCustomStringsFile(CardStrings.class, (String)("ExampleResources/localization/" + lang + "/cards.json"));
        BaseMod.loadCustomStringsFile(CharacterStrings.class, (String)("ExampleResources/localization/" + lang + "/characters.json"));
        BaseMod.loadCustomStringsFile(PowerStrings.class, (String)("ExampleResources/localization/" + lang + "/powers.json"));
        BaseMod.loadCustomStringsFile(RelicStrings.class, (String)("ExampleResources/localization/" + lang + "/relics.json"));
        BaseMod.loadCustomStringsFile(StanceStrings.class, (String)("ExampleResources/localization/" + lang + "/stances.json"));
        BaseMod.loadCustomStringsFile(PotionStrings.class, (String)("ExampleResources/localization/" + lang + "/potions.json"));
    }

    public void receiveEditCharacters() {
        BaseMod.addCharacter((AbstractPlayer)new MyCharacter(CardCrawlGame.playerName), (String)MY_CHARACTER_BUTTON, (String)MY_CHARACTER_PORTRAIT, (AbstractPlayer.PlayerClass)MyCharacter.PlayerColorEnum.MY_CHARACTER);
    }

    public void receiveEditRelics() {
        BaseMod.addRelic((AbstractRelic)new Surname(), (RelicType)RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic)new Surname2(), (RelicType)RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic)new Startingwithme(), (RelicType)RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic)new Celine(), (RelicType)RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic)new Acandleinthefiercewind(), (RelicType)RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic)new DivineFallSwordSilter(), (RelicType)RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic)new HHkey(), (RelicType)RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic)new TheHuberian(), (RelicType)RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic)new Thekeytoemptiness(), (RelicType)RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic)new HeavenFireMark(), (RelicType)RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic)new VoidRealm(), (RelicType)RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic)new FinalityOath(), (RelicType)RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic)new Moonlight(), (RelicType)RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic)new TruthKey(), (RelicType)RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic)new BloodRose(), (RelicType)RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic)new BlackWhiteFlower(), (RelicType)RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic)new EdenStar(), (RelicType)RelicType.SHARED);
    }

    public void receivePostInitialize() {
        this.registerCards();
        // Register events directly (no EditEventSubscriber in this BaseMod version)
        BaseMod.addEvent(SaintFreyaAcademyEvent.ID, SaintFreyaAcademyEvent.class);
        BaseMod.addEvent(ChangKongNightEvent.ID, ChangKongNightEvent.class);
        BaseMod.addEvent(TianMingHeadquartersEvent.ID, TianMingHeadquartersEvent.class);
        BaseMod.addEvent(NiSheProposalEvent.ID, NiSheProposalEvent.class);
        BaseMod.addEvent(KianaDreamEvent.ID, KianaDreamEvent.class);
        BaseMod.addPotion(HonkaiEnergyPotion.class, (Color)Color.WHITE, (Color)new Color(0.98f, 0.98f, 0.98f, 1.0f), (Color)new Color(0.95f, 0.95f, 1.0f, 1.0f), (String)HonkaiEnergyPotion.ID);
        BaseMod.addPotion(AbyssalLawbringerPotion.class, (Color)Color.WHITE, (Color)new Color(0.98f, 0.98f, 0.98f, 1.0f), (Color)new Color(0.95f, 0.95f, 1.0f, 1.0f), (String)AbyssalLawbringerPotion.ID);
        BaseMod.addPotion(AbyssalLawbringer2Potion.class, (Color)Color.BLACK, (Color)Color.WHITE, (Color)new Color(0.5f, 0.5f, 0.5f, 1.0f), (String)AbyssalLawbringer2Potion.ID);
        BaseMod.addPotion(ProtectionPotion.class, (Color)new Color(0.1f, 0.5f, 0.9f, 1.0f), (Color)new Color(0.9f, 0.9f, 0.1f, 1.0f), (Color)new Color(0.4f, 0.7f, 1.0f, 1.0f), (String)ProtectionPotion.ID);
        BaseMod.addPotion(StancePotion2.class, (Color)Color.RED, (Color)Color.LIGHT_GRAY, (Color)Color.DARK_GRAY, (String)StancePotion2.ID);
        BaseMod.addPotion(StancePotion3.class, (Color)new Color(0.7f, 0.3f, 1.0f, 1.0f), (Color)new Color(0.8f, 0.5f, 1.0f, 1.0f), (Color)new Color(0.6f, 0.2f, 0.9f, 1.0f), (String)StancePotion3.ID);
    }

    private void registerCards() {
        BaseMod.addCard((AbstractCard)new ChooseFlame());
        BaseMod.addCard((AbstractCard)new ChooseSpace());
        BaseMod.addCard((AbstractCard)new ChooseEnd());
    }

    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang = Settings.language == Settings.GameLanguage.ZHS ? "ZHS" : "ENG";
        String json = Gdx.files.internal("ExampleResources/localization/" + lang + "/keywords.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = (Keyword[])gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword((String)"examplemod", (String)keyword.NAMES[0], (String[])keyword.NAMES, (String)keyword.DESCRIPTION);
            }
        }
    }
}

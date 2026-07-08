package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kianamod.characters.MyCharacter;
import kianamod.powers.ProtectionPower;
import kianamod.powers.TemporalRiftPower;
import kianamod.util.FlameCardUtils;

/**
 * 绝对时空断裂 (AbsoluteTimeBreak)
 * 费用 2 | RARE | 技能 | 自身 | 消耗
 * 获得 3(5) 层时空裂隙。获得 5(7) 崩坏能。获得 1(2) 层庇护。
 * 原作概念：时滞领域，时间停止，进入无敌爆发状态。
 */
public class AbsoluteTimeBreak extends CustomCard {
    public static final String ID = "ExampleMod:AbsoluteTimeBreak";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/AbsoluteTimeBreak.png";

    private static final int COST = 2;
    private static final int BASE_RIFT = 3;
    private static final int UPG_RIFT = 5;
    private static final int BASE_HONKAI = 5;
    private static final int UPG_HONKAI = 7;
    private static final int BASE_PROTECTION = 1;
    private static final int UPG_PROTECTION = 2;

    public AbsoluteTimeBreak() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.SKILL,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = BASE_RIFT;
        this.magicNumber = this.baseMagicNumber;
        this.baseDamage = BASE_HONKAI;  // repurpose baseDamage for honkai amount display
        this.baseBlock = BASE_PROTECTION;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 时空裂隙
        addToBot(new ApplyPowerAction(p, p, new TemporalRiftPower(p, this.magicNumber), this.magicNumber));
        // 崩坏能
        int honkaiAmt = this.upgraded ? UPG_HONKAI : BASE_HONKAI;
        FlameCardUtils.addHonkai(p, honkaiAmt);
        // 庇护
        int protAmt = this.upgraded ? UPG_PROTECTION : BASE_PROTECTION;
        addToBot(new ApplyPowerAction(p, p, new ProtectionPower(p, protAmt, false), protAmt));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPG_RIFT - BASE_RIFT);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new AbsoluteTimeBreak();
    }
}

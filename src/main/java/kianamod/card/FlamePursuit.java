package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import kianamod.characters.MyCharacter;
import kianamod.stances.FlameStance;
import kianamod.util.FlameCardUtils;

/**
 * 炎之追击 (FlamePursuit)
 * 造成 8(11) 点火焰伤害；若处于薪炎之律者形态，给予目标 2(3) 层易伤；获得 1 点崩坏能。
 */
public class FlamePursuit extends CustomCard {
    public static final String ID = "ExampleMod:FlamePursuit";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/FlamePursuit.png";

    private static final int COST = 1;
    private static final int BASE_DAMAGE = 8;
    private static final int UPG_DAMAGE = 11;
    private static final int BASE_VULN = 2;
    private static final int UPG_VULN = 3;
    private static final int HONKAI = 1;

    public FlamePursuit() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.ATTACK,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = BASE_DAMAGE;
        this.baseMagicNumber = BASE_VULN;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.FIRE));
        if (p.stance instanceof FlameStance) {
            addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
        }
        FlameCardUtils.addHonkai(p, HONKAI);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPG_DAMAGE - BASE_DAMAGE);
            this.upgradeMagicNumber(UPG_VULN - BASE_VULN);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new FlamePursuit();
    }
}

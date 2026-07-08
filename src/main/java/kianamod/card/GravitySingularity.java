package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kianamod.characters.MyCharacter;
import kianamod.powers.BuildupOfDizzinessPower;
import kianamod.util.FlameCardUtils;

/**
 * 引力奇点 (GravitySingularity)
 * 对所有敌人施加 3(5) 层眩晕积蓄。获得 2 崩坏能。
 */
public class GravitySingularity extends CustomCard {
    public static final String ID = "ExampleMod:GravitySingularity";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/GravitySingularity.png";

    private static final int COST = 2;
    private static final int BASE_STUN = 3;
    private static final int UPG_STUN = 5;
    private static final int HONKAI = 2;

    public GravitySingularity() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.SKILL,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = BASE_STUN;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : com.megacrit.cardcrawl.dungeons.AbstractDungeon.getMonsters().monsters) {
            if (!mo.isDead && !mo.isDying) {
                addToBot(new ApplyPowerAction(mo, p,
                        new BuildupOfDizzinessPower(mo, p, this.magicNumber), this.magicNumber));
            }
        }
        FlameCardUtils.addHonkai(p, HONKAI);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPG_STUN - BASE_STUN);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new GravitySingularity();
    }
}

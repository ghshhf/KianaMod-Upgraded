package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kianamod.characters.MyCharacter;

/**
 * 终焉裁决 (FinalJudgment)
 * 费用 2 | RARE | 攻击 | 敌方
 * 造成 20(28) 伤害。若处于终焉之律者形态，改为 30(40) 伤害。
 * 原作概念：终焉之力凝聚的致命一击，形态下倍率飙升。
 */
public class FinalJudgment extends CustomCard {
    public static final String ID = "ExampleMod:FinalJudgment";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/FinalJudgment.png";

    private static final int COST = 2;
    private static final int BASE_DAMAGE = 20;
    private static final int UPG_DAMAGE = 28;
    private static final int END_BASE_DAMAGE = 30;
    private static final int END_UPG_DAMAGE = 40;

    public FinalJudgment() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.ATTACK,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = BASE_DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int dmg = this.damage;
        addToBot(new DamageAction(m, new DamageInfo(p, dmg, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void applyPowers() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p != null && p.stance != null && p.stance.ID.equals("ExampleMod:EndStance")) {
            this.baseDamage = this.upgraded ? END_UPG_DAMAGE : END_BASE_DAMAGE;
        } else {
            this.baseDamage = this.upgraded ? UPG_DAMAGE : BASE_DAMAGE;
        }
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPlayer p = AbstractDungeon.player;
        if (p != null && p.stance != null && p.stance.ID.equals("ExampleMod:EndStance")) {
            this.baseDamage = this.upgraded ? END_UPG_DAMAGE : END_BASE_DAMAGE;
        } else {
            this.baseDamage = this.upgraded ? UPG_DAMAGE : BASE_DAMAGE;
        }
        super.calculateCardDamage(mo);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPG_DAMAGE - BASE_DAMAGE);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new FinalJudgment();
    }
}

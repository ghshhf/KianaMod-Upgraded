package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kianamod.characters.MyCharacter;
import kianamod.util.FlameCardUtils;

/**
 * 奇点爆发 (SingularityBurst)
 * 消耗全部崩坏能。每消耗 1 点崩坏能造成 5(7) 点伤害。
 * 若处于空之律者形态，伤害翻倍（10/14）。
 */
public class SingularityBurst extends CustomCard {
    public static final String ID = "ExampleMod:SingularityBurst";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/SingularityBurst.png";

    private static final int COST = 2;
    private static final int BASE_DMG_PER_HONKAI = 5;
    private static final int UPG_DMG_PER_HONKAI = 7;
    private static final int SPACE_DMG_PER_HONKAI = 10;
    private static final int UPG_SPACE_DMG_PER_HONKAI = 14;

    public SingularityBurst() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.ATTACK,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = BASE_DMG_PER_HONKAI;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int n = FlameCardUtils.getHonkai(p);
        if (n <= 0) {
            return;
        }
        int dmgPerHit;
        if (p.stance != null && p.stance.ID.equals("ExampleMod:SpaceStance")) {
            dmgPerHit = this.upgraded ? UPG_SPACE_DMG_PER_HONKAI : SPACE_DMG_PER_HONKAI;
        } else {
            dmgPerHit = this.upgraded ? UPG_DMG_PER_HONKAI : BASE_DMG_PER_HONKAI;
        }
        for (int i = 0; i < n; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, dmgPerHit, DamageInfo.DamageType.NORMAL),
                    AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }
        FlameCardUtils.reduceHonkai(p, n);
    }

    @Override
    public void applyPowers() {
        // Don't recalculate damage via traditional means - damage is dynamic per honkai consumed
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SingularityBurst();
    }
}

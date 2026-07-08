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

/**
 * 月如白练 (MoonlightBlade)
 * 费用 1 | UNCOMMON | 攻击 | 敌方
 * 造成 6(9) 点伤害。若处于终焉之律者形态，再造成 6(9) 点伤害。
 * 原作概念：月光之剑，双持连击。
 */
public class MoonlightBlade extends CustomCard {
    public static final String ID = "ExampleMod:MoonlightBlade";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/MoonlightBlade.png";

    private static final int COST = 1;
    private static final int BASE_DAMAGE = 6;
    private static final int UPG_DAMAGE = 9;

    public MoonlightBlade() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.ATTACK,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = BASE_DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 第一次攻击
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        // 终焉形态下第二次攻击
        if (p.stance != null && p.stance.ID.equals("ExampleMod:EndStance")) {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                    AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }
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
        return new MoonlightBlade();
    }
}

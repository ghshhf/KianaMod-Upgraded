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
 * 虚空裂斩 (VoidSlash)
 * 造成 8(12) 点伤害。若处于空之律者形态，伤害类型变为 HP_LOSS 且造成 12(16) 点伤害。
 */
public class VoidSlash extends CustomCard {
    public static final String ID = "ExampleMod:VoidSlash";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/VoidSlash.png";

    private static final int COST = 1;
    private static final int BASE_DAMAGE = 8;
    private static final int UPG_DAMAGE = 12;
    private static final int SPACE_DAMAGE = 12;
    private static final int UPG_SPACE_DAMAGE = 16;

    public VoidSlash() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.ATTACK,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = BASE_DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        DamageInfo.DamageType dmgType;
        int dmg;
        if (p.stance != null && p.stance.ID.equals("ExampleMod:SpaceStance")) {
            dmg = this.upgraded ? UPG_SPACE_DAMAGE : SPACE_DAMAGE;
            dmgType = DamageInfo.DamageType.HP_LOSS;
        } else {
            dmg = this.damage;
            dmgType = DamageInfo.DamageType.NORMAL;
        }
        addToBot(new DamageAction(m, new DamageInfo(p, dmg, dmgType),
                AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public void applyPowers() {
        // Recalculate base damage based on stance before applying powers
        AbstractPlayer p = com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
        if (p != null && p.stance != null && p.stance.ID.equals("ExampleMod:SpaceStance")) {
            this.baseDamage = this.upgraded ? UPG_SPACE_DAMAGE : SPACE_DAMAGE;
        } else {
            this.baseDamage = this.upgraded ? UPG_DAMAGE : BASE_DAMAGE;
        }
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPlayer p = com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
        if (p != null && p.stance != null && p.stance.ID.equals("ExampleMod:SpaceStance")) {
            this.baseDamage = this.upgraded ? UPG_SPACE_DAMAGE : SPACE_DAMAGE;
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
        return new VoidSlash();
    }
}

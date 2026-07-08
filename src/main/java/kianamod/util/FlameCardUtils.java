package kianamod.util;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import kianamod.powers.HonkaiEnergy;

/**
 * 崩坏能安全封装工具类，供薪炎之律者卡牌统一读写崩坏能。
 * 所有方法均对空指针与非负做防御，避免运行时异常。
 */
public final class FlameCardUtils {
    private FlameCardUtils() {
        // 工具类，禁止实例化
    }

    /**
     * 读取目标当前的崩坏能层数；无该能力时返回 0。
     *
     * @param c 目标生物，可为 null
     * @return 崩坏能层数
     */
    public static int getHonkai(AbstractCreature c) {
        if (c == null) {
            return 0;
        }
        if (c.hasPower(HonkaiEnergy.POWER_ID)) {
            AbstractPower power = c.getPower(HonkaiEnergy.POWER_ID);
            return power != null ? power.amount : 0;
        }
        return 0;
    }

    /**
     * 为指定目标增加崩坏能（以动作形式入队，与卡牌其它效果顺序一致）。
     *
     * @param c   目标生物
     * @param amt 增加层数，<=0 时直接忽略
     */
    public static void addHonkai(AbstractCreature c, int amt) {
        if (c == null || amt <= 0) {
            return;
        }
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(c, c, new HonkaiEnergy(c, amt), amt));
    }

    /**
     * 削减指定目标的崩坏能（以动作形式入队）。
     *
     * @param c   目标生物
     * @param amt 削减层数，<=0 时直接忽略
     */
    public static void reduceHonkai(AbstractCreature c, int amt) {
        if (c == null || amt <= 0) {
            return;
        }
        AbstractDungeon.actionManager.addToBottom(
                new ReducePowerAction(c, c, HonkaiEnergy.POWER_ID, amt));
    }
}

package aster.amo.tarot.commands.subcommands

import aster.amo.tarot.commands.box.BoxUtils
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.tree.LiteralCommandNode
import aster.amo.tarot.Tarot
import aster.amo.tarot.utils.SubCommand
import me.lucko.fabric.api.permissions.v0.Permissions
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands

class BoxSwapCommand : SubCommand {
    override fun build(): LiteralCommandNode<CommandSourceStack> {
        return Commands.literal("swap")
            .requires(Permissions.require("${Tarot.MOD_ID}.command.box.release", 2))
            .then(Commands.argument("box1", IntegerArgumentType.integer())
                .then(Commands.argument("box2", IntegerArgumentType.integer())
                    .executes { ctx: CommandContext<CommandSourceStack> ->
                        val player = ctx.source.playerOrException
                        BoxUtils.swapBoxes(player, IntegerArgumentType.getInteger(ctx, "box1")-1, IntegerArgumentType.getInteger(ctx, "box2")-1)
                        player.sendMessage(Component.text("Boxes swapped!").color(NamedTextColor.GREEN))
                        1
                    }
                )
            )
            .build()
    }
}

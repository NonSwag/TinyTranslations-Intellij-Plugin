package org.intellij.sdk.language;

import com.intellij.util.containers.BidirectionalMap;
import com.intellij.util.ui.UIUtil;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Constants {

  public static final List<String> TAGS = new ArrayList<>(List.of(
      "msg:", "style:", "click:", "hover:", "repeat:", "darker", "lighter", "url"
  ));
  static {
    Constants.TAGS.addAll(TextDecoration.NAMES.keys());
  }


  public static final BidirectionalMap<NamedTextColor, String> COLOR_ENCODINGS = new BidirectionalMap<>();
  static {
    COLOR_ENCODINGS.put(NamedTextColor.BLACK, "0");
    COLOR_ENCODINGS.put(NamedTextColor.DARK_BLUE, "1");
    COLOR_ENCODINGS.put(NamedTextColor.DARK_GREEN, "2");
    COLOR_ENCODINGS.put(NamedTextColor.DARK_AQUA, "3");
    COLOR_ENCODINGS.put(NamedTextColor.DARK_RED, "4");
    COLOR_ENCODINGS.put(NamedTextColor.DARK_PURPLE, "5");
    COLOR_ENCODINGS.put(NamedTextColor.GOLD, "6");
    COLOR_ENCODINGS.put(NamedTextColor.GRAY, "7");
    COLOR_ENCODINGS.put(NamedTextColor.DARK_GRAY, "8");
    COLOR_ENCODINGS.put(NamedTextColor.BLUE, "9");
    COLOR_ENCODINGS.put(NamedTextColor.GREEN, "a");
    COLOR_ENCODINGS.put(NamedTextColor.AQUA, "b");
    COLOR_ENCODINGS.put(NamedTextColor.RED, "c");
    COLOR_ENCODINGS.put(NamedTextColor.LIGHT_PURPLE, "d");
    COLOR_ENCODINGS.put(NamedTextColor.YELLOW, "e");
    COLOR_ENCODINGS.put(NamedTextColor.WHITE, "f");
  }

  public record Suggestion(Icon icon, String sug, String name) {}

  private static Suggestion ampersandColor(NamedTextColor col, char symbol) {
    return new Suggestion(createImageIcon(new Color(col.value()), 14, 14), "&" + symbol, col.toString().toLowerCase());
  }

  public static List<Suggestion> AMP_SUGGESTIONS_LIST = new ArrayList<>(List.of(
      new Suggestion(null, "&m", "strikethrough")
  ));
  public static List<Suggestion> MM_SUGGESTIONS_LIST = new LinkedList<>(List.of(
          new Suggestion(null, "strikethrough", "strikethrough")
  ));
  static {
    COLOR_ENCODINGS.forEach((k, v) -> AMP_SUGGESTIONS_LIST.add(ampersandColor(k, v.toCharArray()[0])));
    COLOR_ENCODINGS.forEach((k, v) -> MM_SUGGESTIONS_LIST.add(
            new Suggestion(createImageIcon(new Color(k.value()), 14, 14), k.toString().toLowerCase(), k.toString().toLowerCase()))
    );
  }

  public static ImageIcon createImageIcon(Color color, int width, int height) {
    BufferedImage image = UIUtil.createImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = image.createGraphics();
    graphics.setPaint(color);
    graphics.fillRect (0, 0, width, height);
    return new ImageIcon(image);
  }
}

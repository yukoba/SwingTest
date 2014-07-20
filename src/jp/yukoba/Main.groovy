package jp.yukoba

import groovy.transform.TypeChecked

import javax.swing.JFrame
import javax.swing.UIManager
import javax.swing.event.TreeSelectionEvent
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel
import java.awt.event.ActionEvent

@TypeChecked
class Main {
    String count = "0"

    final MainForm mainForm = new MainForm()
    final DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("追加した数")
    final DefaultTreeModel treeModel = new DefaultTreeModel(rootNode)

    Main() {
        mainForm.incrementButton.addActionListener { ActionEvent evt -> onIncrementButton() }

        mainForm.numberTree.model = treeModel
        mainForm.numberTree.addTreeSelectionListener { TreeSelectionEvent evt -> onTreeClick(evt) }

        countUpdated()

        createFrame()
    }

    void onIncrementButton() {
        count = mainForm.countTF.text
        count = ((count as int) + 1) as String

        countUpdated()

        treeModel.insertNodeInto(new DefaultMutableTreeNode(count), rootNode, rootNode.childCount)
        mainForm.numberTree.expandRow(0)
    }

    void onTreeClick(TreeSelectionEvent evt) {
        println "Tree click: ${evt.path.lastPathComponent}"
    }

    void countUpdated() {
        mainForm.countTF.text = count
        mainForm.countLabel.text = count
    }

    void createFrame() {
        def frame = new JFrame("MainForm")
        frame.contentPane = mainForm.mainPanel
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.pack()
        frame.locationByPlatform = true
        frame.visible = true
    }

    static void main(String[] args) {
        UIManager.setLookAndFeel(UIManager.systemLookAndFeelClassName)
        new Main()
    }
}

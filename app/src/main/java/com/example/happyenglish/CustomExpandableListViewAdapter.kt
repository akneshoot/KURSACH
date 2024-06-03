
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ProgressBar
import android.widget.TextView
import com.example.happyenglish.R

class CustomExpandableListViewAdapter(
    private val context: Context,
    private val list_group: List<String>,
    private val list_child: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return list_group.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return list_child[list_group[groupPosition]]?.size ?: 0
    }

    override fun getGroup(groupPosition: Int): Any {
        return list_group[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return list_child[list_group[groupPosition]]?.get(childPosition) ?: ""
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val group_title = getGroup(groupPosition) as String
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.listview_group_layout, null)
        }

        if (groupPosition == 10) {
            val test_progressbar = convertView!!.findViewById<ProgressBar>(R.id.test_progressbar)
            val progressbar_text = convertView.findViewById<TextView>(R.id.progressbar_text)
            progressbar_text.visibility = View.VISIBLE
            test_progressbar.visibility = View.VISIBLE

            progressbar_text.text = "${getOverallAverageFromSharedPreferences()}%"
            test_progressbar.progress = getOverallAverageFromSharedPreferences().toInt()
        } else {
            val test_progressbar = convertView!!.findViewById<ProgressBar>(R.id.test_progressbar)
            val progressbar_text = convertView.findViewById<TextView>(R.id.progressbar_text)
            progressbar_text.visibility = View.GONE
            test_progressbar.visibility = View.GONE
        }

        val group_textview = convertView.findViewById<TextView>(R.id.group_textview)
        group_textview.text = group_title

        return convertView
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val child_titles = getChild(groupPosition, childPosition) as String
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.listview_child_layout, null)
        }

        if (groupPosition == 10) {
            val test_progressbar = convertView!!.findViewById<ProgressBar>(R.id.test_progressbar)
            val progressbar_text = convertView.findViewById<TextView>(R.id.progressbar_text)
            progressbar_text.visibility = View.VISIBLE
            test_progressbar.visibility = View.VISIBLE

            test_progressbar.progressDrawable.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN)

            when (childPosition) {
                0 -> {
                    progressbar_text.text = "${geta1AverageFromSharedPreferences()}%"
                    test_progressbar.progress = geta1AverageFromSharedPreferences().toInt()
                }
                1 -> {
                    progressbar_text.text = "${geta2AverageFromSharedPreferences()}%"
                    test_progressbar.progress = geta2AverageFromSharedPreferences().toInt()
                }
                2 -> {
                    progressbar_text.text = "${getb1AverageFromSharedPreferences()}%"
                    test_progressbar.progress = getb1AverageFromSharedPreferences().toInt()
                }
                3 -> {
                    progressbar_text.text = "${getb2AverageFromSharedPreferences()}%"
                    test_progressbar.progress = getb2AverageFromSharedPreferences().toInt()
                }
                4 -> {
                    progressbar_text.text = "${getc1AverageFromSharedPreferences()}%"
                    test_progressbar.progress = getc1AverageFromSharedPreferences().toInt()
                }
                5 -> {
                    progressbar_text.text = "${getc2AverageFromSharedPreferences()}%"
                    test_progressbar.progress = getc2AverageFromSharedPreferences().toInt()
                }
            }
        } else {
            val test_progressbar = convertView!!.findViewById<ProgressBar>(R.id.test_progressbar)
            val progressbar_text = convertView.findViewById<TextView>(R.id.progressbar_text)
            progressbar_text.visibility = View.GONE
            test_progressbar.visibility = View.GONE
        }

        val child_textview = convertView.findViewById<TextView>(R.id.child_textview)
        child_textview.text = child_titles
        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    private fun getOverallAverageFromSharedPreferences(): Float {
        val a1_average = context.getSharedPreferences("a1_average", Context.MODE_PRIVATE).getFloat("a1_average", 0f)
        val a2_average = context.getSharedPreferences("a2_average", Context.MODE_PRIVATE).getFloat("a2_average", 0f)
        val b1_average = context.getSharedPreferences("b1_average", Context.MODE_PRIVATE).getFloat("b1_average", 0f)
        val b2_average = context.getSharedPreferences("b2_average", Context.MODE_PRIVATE).getFloat("b2_average", 0f)
        val c1_average = context.getSharedPreferences("c1_average", Context.MODE_PRIVATE).getFloat("c1_average", 0f)
        val c2_average = context.getSharedPreferences("c2_average", Context.MODE_PRIVATE).getFloat("c2_average", 0f)

        return (a1_average + a2_average + b1_average + b2_average + c1_average + c2_average) / 600 * 100
    }

    private fun geta1AverageFromSharedPreferences(): Float {
        return context.getSharedPreferences("a1_average", Context.MODE_PRIVATE).getFloat("a1_average", 0f)
    }

    private fun geta2AverageFromSharedPreferences(): Float {
        return context.getSharedPreferences("a2_average", Context.MODE_PRIVATE).getFloat("a2_average", 0f)
    }

    private fun getb1AverageFromSharedPreferences(): Float {
        return context.getSharedPreferences("b1_average", Context.MODE_PRIVATE).getFloat("b1_average", 0f)
    }

    private fun getb2AverageFromSharedPreferences(): Float {
        return context.getSharedPreferences("b2_average", Context.MODE_PRIVATE).getFloat("b2_average", 0f)
    }

    private fun getc1AverageFromSharedPreferences(): Float {
        return context.getSharedPreferences("c1_average", Context.MODE_PRIVATE).getFloat("c1_average", 0f)
    }

    private fun getc2AverageFromSharedPreferences(): Float {
        return context.getSharedPreferences("c2_average", Context.MODE_PRIVATE).getFloat("c2_average", 0f)
    }
}

object ExpandableListData {
    fun getData(): HashMap<String, List<String>> {
        val expandableList = LinkedHashMap<String, List<String>>()

        val englishTenses = listOf(
            "Present simple", "Present continuous", "Present perfect", "Present perfect continuous",
            "Past simple", "Past continuous", "Past perfect", "Past perfect continuous",
            "Future simple", "Future continuous", "Future perfect", "Future perfect continuous"
        )

        val englishGrammarTest = listOf(
            "Level A1", "Level A2", "Level B1", "Level B2", "Level C1", "Level C2"
        )

        val idioms = listOf(
            "Idioms", "Metaphors", "Similes"
        )

        val relativeClauses = listOf(
            "Defining Relative Clauses", "Non-defining Relative Clauses"
        )

        val activePassive = listOf(
            "Active/Passive", "Personal/Impersonal Passive"
        )

        val conditionals = listOf(
            "Zero Conditional", "1st Conditional", "2nd Conditional", "3rd Conditional", "Mix Conditional"
        )

        val reportedSpeech = listOf(
            "Direct/Indirect Speech", "Reporting Questions", "Reporting Commands/Requests"
        )

        val infinitivesGerund = listOf(
            "Gerunds", "Infinitives", "Infinitives or Gerund"
        )

        val verbs = listOf(
            "Full Verb", "Auxiliary Verbs", "Modal Auxiliary Verbs"
        )

        val articles = listOf(
            "Definite & Indefinite Article"
        )

        val possessives = listOf(
            "Adjectives & Pronoun"
        )

        val partsofSpeech = listOf(
            "1000 base words"
        )

        val adverbs = listOf(
            "Adverbs of Manner", "Adverbs of Place", "Adverbs of Time", "Adverbs of Frequency"
        )

        expandableList["Vocabulary"] = partsofSpeech
        expandableList["Twelve verb tenses"] = englishTenses
        expandableList["Articles"] = articles
        expandableList["Possessives"] = possessives
        expandableList["Conditionals"] = conditionals
        expandableList["Infinitive & Gerund"] = infinitivesGerund
        expandableList["Relative clauses"] = relativeClauses
        expandableList["Reported speech"] = reportedSpeech
        expandableList["Verbs"] = verbs
        expandableList["Idioms"] = idioms
        expandableList["Grammar tests"] = englishGrammarTest

        return expandableList
    }
}

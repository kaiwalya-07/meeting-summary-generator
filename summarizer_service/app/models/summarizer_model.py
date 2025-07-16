from transformers import pipeline

class SummarizerModel:
    def __init__(self):
        self.pipeline = pipeline("text2text-generation", model="google/flan-t5-small")

    def summarize(self, text: str) -> dict:
        prompt = f"""
        Summarize the following meeting transcript.

        Also extract:

        - Action Items
        - Questions

        Transcript:
        {text}
        """

        output = self.pipeline(prompt, max_length=512, do_sample=False)[0]['generated_text']

        # Parse output
        result = {
            'summary': '',
            'actionItems': [],
            'questions': []
        }

        for line in output.split('\n'):
            if line.lower().startswith("summary:"):
                result['summary'] = line.split(":", 1)[1].strip()
            elif line.lower().startswith("action item"):
                result['actionItems'].append(line.split(":", 1)[1].strip())
            elif line.lower().startswith("question"):
                result['questions'].append(line.split(":", 1)[1].strip())

        return result

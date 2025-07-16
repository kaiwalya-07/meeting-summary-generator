from transformers import pipeline

class SummarizerModel:
    def __init__(self):
        self.summarizer = pipeline("summarization", model="t5-small")

    def summarize(self, text: str) -> str:
        summary = self.summarizer(text, max_length=60, min_length=20, do_sample=False)
        return summary[0]['summary_text']

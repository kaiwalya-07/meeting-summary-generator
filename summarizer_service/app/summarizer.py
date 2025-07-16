from app.models.summarizer_model import SummarizerModel

model = SummarizerModel()

def generate_summary(transcript_text):
    return model.summarize(transcript_text)
